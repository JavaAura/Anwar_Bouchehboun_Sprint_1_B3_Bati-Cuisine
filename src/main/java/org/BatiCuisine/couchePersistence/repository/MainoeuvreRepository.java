package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Interface.ComposantInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MainoeuvreRepository implements ComposantInterface<Mainœuvre> {

    public MainoeuvreRepository(){

    }


    @Override
    public void create(Mainœuvre mainœuvre) {
        String sql = "INSERT INTO public.mainoeuvre(nom, type_composant, projet_id, tauxhoraire, heurestravail, productiviteouvrier) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, mainœuvre.getNom());
            statement.setString(2, mainœuvre.getTypeComposant());
            statement.setInt(3, mainœuvre.getProjet().getId());
            statement.setDouble(4, mainœuvre.getTauxHoraire());
            statement.setDouble(5, mainœuvre.getHeuresTravail());
            statement.setDouble(6, mainœuvre.getProductiviteOuvrier());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                LoggerMessage.info("Mainœuvre  success.");
            } else {
                LoggerMessage.warn("No Succes failed.");
            }
        } catch (SQLException e) {
            LoggerMessage.error(" Error: " + e.getMessage());
        }
    }


    @Override
    public HashMap<String, Mainœuvre> getAll() {
        return null;
    }
}
