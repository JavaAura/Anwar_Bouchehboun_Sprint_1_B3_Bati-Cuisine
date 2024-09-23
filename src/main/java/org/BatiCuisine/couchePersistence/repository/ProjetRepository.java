package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;
import org.BatiCuisine.CoucheMetier.Interface.ProjetInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
public class ProjetRepository implements ProjetInterface<Projet> {
      HashMap<String,Projet> projetHashMap=new HashMap<>();
    @Override
    public Projet create(Projet projet) {
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
            LoggerMessage.error("Error "+e.getMessage());
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
                p.setId(rs.getInt("id"));
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
            LoggerMessage.error("Error: " + e.getMessage());
        }

        return projetHashMap;
    }

    @Override
    public void update(Projet projet) {
        String sql = "UPDATE projet SET marge_beneficiaire=?, cout_total=? WHERE id=?";
        try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            if (projet.getMargeBeneficiaire() == 0) {
                statement.setNull(1, java.sql.Types.DOUBLE);
            } else {
                statement.setDouble(1, projet.getMargeBeneficiaire());
            }
            statement.setDouble(1, projet.getMargeBeneficiaire());
            statement.setDouble(2, projet.getCoutTotal());
            statement.setInt(3, projet.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
               LoggerMessage.info("Projet mis à jour avec succès.");
            } else {
                LoggerMessage.info("Aucun projet trouvé avec cet ID.");
            }

        } catch (SQLException e) {
            LoggerMessage.error("Erreur: " + e.getMessage());
        }
    }

    @Override
    public Projet findByName(String projet) {
        String sql = "SELECT p.id, p.nom_projet, p.surface, p.etat_projet,p.cout_total,p.marge_beneficiaire, c.nom,c.adrresse,c.telephone FROM projet p JOIN client c ON p.client_id = c.id WHERE p.nom_projet LIKE ?";
        Projet p = null;

        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, "%" + projet + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Projet();
                    p.setId(rs.getInt("id"));
                    p.setNomProjet(rs.getString("nom_projet"));
                    p.setCoutTotal(rs.getDouble("cout_total"));
                    String etat = rs.getString("etat_projet");
                    p.setEtatProjet(EtatProjet.valueOf(etat.toUpperCase()));
                    p.setMargeBeneficiaire(rs.getDouble("marge_beneficiaire"));
                    p.setSurface(rs.getDouble("surface"));
                    Client client = new Client();
                    client.setNom(rs.getString("nom"));
                    client.setAdrresse(rs.getString("adrresse"));
                    client.setTelephone(rs.getString("telephone"));
                    p.setClient(client);
                } else {
                    LoggerMessage.info("No project found with the name: " + projet);
                }
            }
        } catch (SQLException e) {
            LoggerMessage.error("Error: " + e.getMessage());
        }

        return p;
    }







}
