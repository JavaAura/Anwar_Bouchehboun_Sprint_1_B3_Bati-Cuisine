package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;
import org.BatiCuisine.CoucheMetier.Interface.RepoInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProjetRepository implements RepoInterface<Projet> {

    @Override
    public Projet create(Projet projet) {
        System.out.println(projet);
        String sql="INSERT INTO projet(nom_projet,etat_projet,client_id,surface) VALUES (?,?,?,?)RETURNING id";

        try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, projet.getNomProjet());
            statement.setString(2, projet.getEtatProjet().name());
            statement.setInt(3, projet.getClient().getId());
            statement.setDouble(4, projet.getSurface());

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {

                projet.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error "+e.getSQLState());
        }
        return projet;
    }

    @Override
    public List<Projet> getAll() {
        return List.of();
    }
}
