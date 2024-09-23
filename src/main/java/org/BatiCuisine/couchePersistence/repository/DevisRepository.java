package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Interface.RepoInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DevisRepository implements RepoInterface<Devis> {


    @Override
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


    @Override
    public List<Devis> getAll() {
        return List.of();
    }
}
