package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Interface.ComposantInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainoeuvreRepository implements ComposantInterface<Mainœuvre> {


    HashMap<String,Mainœuvre> mainœuvreHashMap=new HashMap<>();
    public MainoeuvreRepository(){

    }


    @Override
    public void create(Mainœuvre mainœuvre) {
        String sql = "INSERT INTO mainoeuvre(nom, type_composant, projet_id, tauxhoraire, heurestravail, productiviteouvrier) VALUES (?, ?, ?, ?, ?, ?)";

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
    public HashMap<String, Mainœuvre> getAllMain() {
        String sql="SELECT m.id, m.nom,m.type_composant,m.tauxhoraire,m.heurestravail,m.productiviteouvrier,p.nom_projet FROM mainoeuvre m,projet p where m.projet_id=p.id";
        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mainœuvre p = new Mainœuvre();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setTypeComposant(rs.getString("type_composant"));
                p.setTauxHoraire(rs.getDouble("tauxhoraire"));
                p.setHeuresTravail(rs.getDouble("heurestravail"));
                p.setProductiviteOuvrier(rs.getDouble("productiviteouvrier"));

                Projet projet = new Projet();
                projet.setNomProjet(rs.getString("nom_projet"));
                    p.setProjet(projet);
                mainœuvreHashMap.put(p.getProjet().getNomProjet(),p);
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error: " + e.getMessage());
        }

        return mainœuvreHashMap;
    }

    public List<Mainœuvre> getAllMain(Projet projet) {
        String sql = "SELECT m.id, m.nom, m.type_composant, m.tauxhoraire, m.heurestravail, m.productiviteouvrier,m.taux_tva " +
                "FROM mainoeuvre m, projet p " +
                "WHERE m.projet_id = p.id AND p.nom_projet = ?";
        List<Mainœuvre> mainoeuvreList = new ArrayList<>();

        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, projet.getNomProjet());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mainœuvre mainoeuvre = new Mainœuvre();
                    mainoeuvre.setId(rs.getInt("id"));
                    mainoeuvre.setNom(rs.getString("nom"));
                    mainoeuvre.setTypeComposant(rs.getString("type_composant"));
                    mainoeuvre.setTauxHoraire(rs.getDouble("tauxhoraire"));
                    mainoeuvre.setHeuresTravail(rs.getDouble("heurestravail"));
                    mainoeuvre.setProductiviteOuvrier(rs.getDouble("productiviteouvrier"));
                    mainoeuvre.setTauxTva(rs.getDouble("taux_tva"));
                     projet.ajouterMainOuvrier(mainoeuvre);
                     mainoeuvreList.add(mainoeuvre);

                }
            }
        } catch (SQLException e) {
            LoggerMessage.error("Error: " + e.getMessage());
        }
    return mainoeuvreList;
    }

}
