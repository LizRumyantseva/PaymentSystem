package test;

import domain.Client;
import org.testng.Assert;
import org.testng.annotations.Test;
import repository.*;
import services.CardService;
import services.CardServiceImpl;
import services.ClientService;
import services.ClientServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestPaymentSystem {
    private  ClientDAO clientsRep = new MySQLClientDAOImpl();
    private ClientService clients = new ClientServiceImpl(clientsRep);
    private  CardDAO cardsRep = new MySQLCardDAOImpl();
    private  CardService cards = new CardServiceImpl(cardsRep);

    @Test
    public void testAdd() throws ParseException {
        // this method ensures adding a new unic client to DB
        String bdate = "1992-10-26";
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date y = s.parse(bdate);
        Client newClient = new Client("LizaTest", "RumyantsevaTest", y);
        clients.add(newClient);

        Boolean isRetrieved = false;
        Connection connection = null;
        Statement statement = null;
        String findClient = "SELECT first_name, last_name, birth_date FROM clients WHERE (first_name, last_name, birth_date) = ('LizaTest', 'RumyantsevaTest', '1992-10-26') ";
        try {
            connection = ConnectionJDBC.getConnection();
            statement = connection.createStatement();
            isRetrieved = statement.execute(findClient);
            System.out.println(isRetrieved);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionJDBC.close(statement);
            ConnectionJDBC.close(connection);
        }
        Assert.assertTrue(isRetrieved);
    }
}



