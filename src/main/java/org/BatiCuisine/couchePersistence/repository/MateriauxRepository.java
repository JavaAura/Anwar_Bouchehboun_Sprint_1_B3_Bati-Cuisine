package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
import org.BatiCuisine.CoucheMetier.Interface.ComposantInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class MateriauxRepository implements ComposantInterface<Materiaux> {

    public  MateriauxRepository(){

    }


    @Override
    public void create(Materiaux materiaux) {
        String sql="INSERT INTO materiel(nom,type_composant, projet_id, coutunitaire, quantite, couttransport, coefficientqualite) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, materiaux.getNom());
            statement.setString(2, materiaux.getTypeComposant());
            statement.setInt(3, materiaux.getProjet().getId());
            statement.setDouble(4, materiaux.getCoutUnitaire());
            statement.setDouble(5, materiaux.getQuantite());
            statement.setDouble(6, materiaux.getCoutTransport());
            statement.setDouble(7,materiaux.getCoefficientQualite());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                LoggerMessage.info("materiaux  success.");
            } else {
                LoggerMessage.warn("No Succes failed.");
            }
        } catch (SQLException e) {
            LoggerMessage.error(" Error: " + e.getMessage());
        }
    }

    @Override
    public HashMap<String, Materiaux> getAll() {
        return null;
    }
}
