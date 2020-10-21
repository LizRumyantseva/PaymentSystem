package services;

import domain.Card;
import domain.Client;
import repository.ClientDAO;

import java.util.List;

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
    public void delete(int card_number) {
        clientRep.delete(card_number);
    }

    @Override
    public Client findClientById(int id) {
        return clientRep.findClientById(id);
    }

    @Override
    public List<Client> getAll() { return clientRep.getAll(); }

    @Override
    public List<Card> getClientCards(int id) {
        return clientRep.getClientCards(id);
    }
}
