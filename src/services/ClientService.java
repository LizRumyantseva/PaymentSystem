package services;

import domain.Client;

public interface ClientService {
    void add(Client client);
    Client findClientById(int id);
}
