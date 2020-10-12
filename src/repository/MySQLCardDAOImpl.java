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
            stInsertCard.setInt(1, card.getNumber());
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
}
