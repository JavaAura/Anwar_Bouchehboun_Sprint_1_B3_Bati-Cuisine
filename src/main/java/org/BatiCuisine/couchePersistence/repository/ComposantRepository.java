package org.BatiCuisine.couchePersistence.repository;


import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.SQLException;


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

}
