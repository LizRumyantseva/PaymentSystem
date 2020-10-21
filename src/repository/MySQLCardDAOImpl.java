package repository;

import domain.Card;
import domain.Client;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySQLCardDAOImpl implements CardDAO {

    @Override
    public void add(Card card) {
        String insertCard = "INSERT INTO cards (card_number, expiry_date, client_id) VALUES (?, ?, ?)";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stInsertCard = connection.prepareStatement(insertCard);
            stInsertCard.setString(1, card.getNumber());
            stInsertCard.setDate(2, new java.sql.Date(card.getExpDate().getTime()));
            stInsertCard.setInt(3, card.getClientId());
            stInsertCard.executeUpdate();
            System.out.println("Card was successfully added.");
            //System.out.println("данные добавлены");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Невозможно присвоить клиенту уже имеющийся номер карты");
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
    }

    @Override
    public void delete(String card_number) {
        String deleteCard = "DELETE FROM cards WHERE card_number = ?";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stDeleteCard = connection.prepareStatement(deleteCard);
            stDeleteCard.setString(1, card_number);
            int i = stDeleteCard.executeUpdate();
            System.out.println(i + " cards was successfully deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
    }

    @Override
    public List<Card> getAll() {
        Connection connection = null;
        Statement statement = null;
        String findCard = "SELECT * FROM cards";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            List<Card> cards = new ArrayList();
            ResultSet rs = statement.executeQuery(findCard);
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
    public void receiveMoney(int id, double sum) {
        Connection connection = null;
        Statement statement = null;
        String getClientCards = "UPDATE cards SET balance = ? where id = ?";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(getClientCards);

            stmt.setDouble(1, sum);
            stmt.setInt(2, id);
            int i = stmt.executeUpdate();
            System.out.println(i + " cards changed its balance.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
    }
}
