package repository;

import domain.Client;

public interface ClientDAO extends DAOGeneral<Client> {
    void add(Client client);
    Client findClientById(int id);
}
