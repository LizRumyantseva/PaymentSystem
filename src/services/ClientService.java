package services;

import domain.Card;
import domain.Client;

import java.util.List;

public interface ClientService {
    void add(Client client);
    void delete(int card_number);
    Client findClientById(int id);
    List<Client> getAll();
    List<Card> getClientCards(int id);
}
