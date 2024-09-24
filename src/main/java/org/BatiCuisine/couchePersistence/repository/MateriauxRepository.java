package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
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

public class MateriauxRepository implements ComposantInterface<Materiaux> {

    public  MateriauxRepository(){

    }
    HashMap<String,Materiaux> materiauxHashMap=new HashMap<>();


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
    public HashMap<String, Materiaux> getAllMain() {
        String sql="SELECT  m.id,m.nom, m.type_composant,m.coutunitaire,m.quantite,m.couttransport,m.coefficientqualite,p.nom_projet FROM materiel m, projet p WHERE m.projet_id = p.id";
        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Materiaux p = new Materiaux();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setTypeComposant(rs.getString("type_composant"));
                p.setCoutUnitaire(rs.getDouble("coutunitaire"));
                p.setCoefficientQualite(rs.getDouble("coefficientqualite"));
                p.setCoutTransport(rs.getDouble("couttransport"));
                p.setQuantite(rs.getDouble("quantite"));

                Projet projet = new Projet();
                projet.setNomProjet(rs.getString("nom_projet"));
                p.setProjet(projet);
                materiauxHashMap.put(String.valueOf(p.getId()), p);
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error: " + e.getMessage());
        }

        return materiauxHashMap;
    }

    @Override
    public List<Materiaux> getAllMainData(Projet projet) {
        String sql = "SELECT m.id, m.nom, m.type_composant, m.quantite, m.couttransport, m.coefficientqualite,m.coutunitaire,m.taux_tva " +
                "FROM materiel m, projet p " +
                "WHERE m.projet_id = p.id AND p.nom_projet = ?";
        List<Materiaux> materiauxes = new ArrayList<>();

        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            stmt.setString(1, projet.getNomProjet());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Materiaux materiaux = new Materiaux();
                    materiaux.setId(rs.getInt("id"));
                    materiaux.setNom(rs.getString("nom"));
                    materiaux.setTypeComposant(rs.getString("type_composant"));
                    materiaux.setQuantite(rs.getDouble("quantite"));
                    materiaux.setCoutTransport(rs.getDouble("couttransport"));
                    materiaux.setCoefficientQualite(rs.getDouble("coefficientqualite"));
                    materiaux.setCoutUnitaire(rs.getDouble("coutunitaire"));
                    materiaux.setTauxTva(rs.getDouble("taux_tva"));
                    projet.ajouterMatriaux(materiaux);
                    materiauxes.add(materiaux);

                }
            }
        } catch (SQLException e) {
            LoggerMessage.error("Error: " + e.getMessage());
        }
        return materiauxes;
    }
}
