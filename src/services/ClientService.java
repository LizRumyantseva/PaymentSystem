package services;

import domain.Client;

public interface ClientService {
    public void add(Client client);
    public Client findClientById(int id);
}
