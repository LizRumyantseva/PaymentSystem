package repository;

import domain.Card;
import domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLClientDAOImpl implements ClientDAO {

    @Override
    public void add(Client client) {
        String insertClient = "INSERT INTO clients (first_name, last_name, birth_date) VALUES (?, ?, ?)";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stInsertClient = connection.prepareStatement(insertClient);
            stInsertClient.setString(1, client.getFName());
            stInsertClient.setString(2, client.getLName());
            stInsertClient.setDate(3, new java.sql.Date(client.getBDate().getTime()));
            stInsertClient.executeUpdate();
            System.out.println("Client was successfully added.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
    }

    @Override
    public Client findClientById(int id) {
        Connection connection = null;
        Statement statement = null;
        String clientById = "SELECT first_name, last_name, birth_date FROM clients WHERE id = ?";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(clientById);
            stmt.setInt(1, id);
            ResultSet resSet = stmt.executeQuery();
            if (resSet.next()) {
                return new Client(resSet.getString("first_name"), resSet.getString("last_name"), resSet.getDate("birth_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        Connection connection = null;
        Statement statement = null;
        String findClient = "SELECT * FROM clients";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            List<Client> clients = new ArrayList();
            ResultSet rs = statement.executeQuery(findClient);
            while (rs.next())
                clients.add(new Client(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("birth_date")));
            return clients;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
        return null;
    }

    @Override
    public List<Card> getClientCards(int id) {
        Connection connection = null;
        Statement statement = null;
        String getClientCards = "SELECT * from cards where client_id = ?";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(getClientCards);

            stmt.setInt(1, id);
            List<Card> cards = new ArrayList();
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                cards.add(new Card(rs.getInt("id"), rs.getString("card_number"), rs.getDate("expiry_date"), rs.getDouble("balance"), rs.getInt("client_id")));
            return cards;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        String deleteClient = "DELETE FROM clients WHERE id = ?";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stDeleteClient = connection.prepareStatement(deleteClient);
            stDeleteClient.setInt(1, id);
            int i = stDeleteClient.executeUpdate();
            System.out.println(i + " client(s) with their cards was successfully deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
    }
}
