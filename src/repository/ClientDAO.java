package repository;

import domain.Client;

public interface ClientDAO extends DAOGeneral<Client> {
    public void add(Client client);
    public Client findClientById(int id);
}
