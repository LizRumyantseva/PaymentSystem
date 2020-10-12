package console;

import domain.Client;
import repository.CardDAO;
import repository.ClientDAO;
import repository.MySQLCardDAOImpl;
import repository.MySQLClientDAOImpl;
import services.CardService;
import services.CardServiceImpl;
import services.ClientService;
import services.ClientServiceImpl;


//import java.sql.Connection;


public class Main {
    private static ClientDAO clientsRep;
    private static ClientService clients;
    private static CardDAO cardsRep;
    private static CardService cards;

    public static void main(String[] args) throws Exception {
        clientsRep = new MySQLClientDAOImpl();
        clients = new ClientServiceImpl(clientsRep);

        cardsRep = new MySQLCardDAOImpl();
        cards = new CardServiceImpl(cardsRep);

        MenuProcessing.launchMainMenu(clients, cards);
    }
}
