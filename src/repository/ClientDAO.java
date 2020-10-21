package repository;

import domain.Card;
import domain.Client;

import java.util.List;

public interface ClientDAO extends DAOGeneral<Client,Integer> {
    void add(Client client);
    void delete(Integer id);
    Client findClientById(int id);
    List<Client> getAll();
    List<Card> getClientCards (int id);

}
