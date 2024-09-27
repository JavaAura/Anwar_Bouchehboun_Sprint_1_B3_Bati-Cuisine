package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DevisRepository  {



    public Devis create(Devis devis) {

        String sql = "INSERT INTO devis(montantestime, dateemission,datevalidite,accepte, projet_id) VALUES (?, ?,?,?, ?) RETURNING id";

        try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            statement.setDouble(1, devis.getProjet().getCoutTotal());
            LocalDate date =LocalDate.now();
            statement.setDate(2, java.sql.Date.valueOf(date));
            LocalDate dateValidite = devis.getDateValidite();
            statement.setDate(3,java.sql.Date.valueOf(dateValidite));
            statement.setBoolean(4, devis.isAccepte());
            statement.setInt(5, devis.getProjet().getId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        devis.setId(generatedKeys.getInt(1));
                    }
                }
                LoggerMessage.info("Devis créé avec succès.");
            }
        } catch (SQLException e) {
            LoggerMessage.error("Erreur lors de la création du devis : " + e.getMessage());
        }

        return devis;
    }

    public void accepteDevis(Devis devis) {
        String sql = "UPDATE devis SET accepte = ? WHERE projet_id = ?";

        try (
             PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            preparedStatement.setBoolean(1, true);

            preparedStatement.setInt(2, devis.getProjet().getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                LoggerMessage.info("Devis accepted successfully!");
            } else {
               LoggerMessage.warn("No Devis found with the given projet_id.");
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error while accepting devis: " + e.getMessage());

        }
    }


    public Devis valideDevis(Projet projet) {
        String sql = "SELECT accepte FROM devis WHERE projet_id = ?";

        Devis devis = null;

        try (
             PreparedStatement pstmt =  DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstmt.setInt(1, projet.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    devis = new Devis();
                    devis.setAccepte(rs.getBoolean("accepte"));

                }
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error while checking Devis acceptance: " + e.getMessage());
        }

        return devis;
    }


}
