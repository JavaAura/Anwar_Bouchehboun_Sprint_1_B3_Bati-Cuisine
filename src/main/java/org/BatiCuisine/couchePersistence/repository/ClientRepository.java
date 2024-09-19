package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Interface.RepoInterface;
import org.BatiCuisine.coucheUtilitaire.DbConnection;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements RepoInterface<Client> {
    @Override
    public Client create(Client client) {

        String sql = "INSERT INTO public.client(nom, adrresse, telephone, estprofessionnel) VALUES ( ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAdrresse());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.isEstProfessionnel());

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {

                client.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error "+e.getSQLState());
        }
        return client;

    }



    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setNom(rs.getString("nom"));
                client.setTelephone(rs.getString("telephone"));
                client.setAdrresse(rs.getString("adrresse"));
                client.setEstProfessionnel(rs.getBoolean("estprofessionnel"));
                clients.add(client);
            }

        } catch (SQLException e) {
            LoggerMessage.error("Error " + e.getSQLState());
        }

        return clients;
    }

}
