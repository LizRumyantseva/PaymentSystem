package services;

import domain.Client;

public interface ClientService {
    void add(Client client);
    void delete(int card_number);
    Client findClientById(int id);
}
