package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Interface.ProjetInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjetRepository implements ProjetInterface<Projet> {
      HashMap<String,Projet> projetHashMap=new HashMap<>();
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
    public HashMap<String, Projet> getAll() {

        String sql = "SELECT p.id, p.nom_projet, p.surface, p.etat_projet,c.nom FROM projet p JOIN client c ON p.client_id = c.id";

        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projet p = new Projet();
                p.setId(rs.getInt("id"));  // Corrected: Fetching 'id' from 'projet'
                p.setNomProjet(rs.getString("nom_projet"));
                String etat = rs.getString("etat_projet");
                p.setEtatProjet(p.getEtatProjet().valueOf(etat.toUpperCase()));
                p.setSurface(rs.getDouble("surface"));
                Client client = new Client();
                client.setNom(rs.getString("nom"));
                p.setClient(client);
                projetHashMap.put(p.getNomProjet(),p);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return projetHashMap;
    }

}
