package org.BatiCuisine.couchePersistence.repository;


import org.BatiCuisine.CoucheMetier.Entite.Composant;
import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ComposantRepository  {



    public void update(double tva,int id) {

        String sql = "UPDATE composant SET taux_tva= ? WHERE projet_id= ?";
     try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            statement.setDouble(1, tva);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                LoggerMessage.info("Taux tva mis à jour avec succès.");
            } else {
                LoggerMessage.info("Aucun Composant trouvé avec cet Projet_id.");
            }

        } catch (SQLException e) {
            LoggerMessage.error("Erreur: " + e.getMessage());
        }
    }


    public double getTva(Projet projet) {
        String sql = "SELECT m.taux_tva \n" +
                "FROM composant m, projet p \n" +
                "WHERE p.nom_projet = ? \n" +
                "AND p.id = m.projet_id \n" +
                "LIMIT 1";
        double tva = 0.0;

        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, projet.getNomProjet());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tva = rs.getDouble("taux_tva");
                }
            }
        } catch (SQLException e) {
            LoggerMessage.error("Error: " + e.getMessage());
        }

        return tva;
    }


}
