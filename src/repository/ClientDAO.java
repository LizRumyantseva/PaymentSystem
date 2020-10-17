package repository;

import domain.Client;

public interface ClientDAO extends DAOGeneral<Client,Integer> {
    void add(Client client);
    void delete(Integer id);
    Client findClientById(int id);
}
