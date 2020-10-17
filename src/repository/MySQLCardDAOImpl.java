package repository;

import domain.Card;
import java.sql.*;

public class MySQLCardDAOImpl implements CardDAO{

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
        } catch(SQLIntegrityConstraintViolationException e){
            System.err.println("Невозможно присвоить клиенту уже имеющийся номер карты");
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        finally {
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
            System.out.println(i +" cards was successfully deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
    }
}
