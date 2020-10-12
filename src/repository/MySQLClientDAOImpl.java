package repository;

import domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            //i = statement.executeUpdate("INSERT INTO clients (name) VALUES ('NewIgor');");
            System.out.println("Client was successfully added.");
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }

    }

    @Override
    public Client findClientById(int id){
        Connection connection = null;
        Statement statement = null;
        String clientById = "SELECT first_name, last_name, birth_date FROM clients WHERE id = ?";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(clientById);

            stmt.setInt(1, id);
            ResultSet resSet = stmt.executeQuery();
            if (resSet.next()){
                return new Client(resSet.getString("first_name"),resSet.getString("last_name"),resSet.getDate("birth_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
        return null;
    }
}
