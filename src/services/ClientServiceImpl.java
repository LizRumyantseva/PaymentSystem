package services;

import domain.Client;
import repository.ClientDAO;

public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientRep;

    public ClientServiceImpl(ClientDAO clientRep) {
        this.clientRep = clientRep;
    }

    @Override
    public void add(Client client) {
        clientRep.add(client);
    }

    @Override
    public Client findClientById(int id) {
        return clientRep.findClientById(id);
    }
}
