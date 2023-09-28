package com.reservation.uqac.business;

import com.reservation.uqac.domaine.client.ClientDomaine;

import java.util.List;

public interface ClientInterface {
    public ClientDomaine getClient(ClientDomaine client);

    public ClientDomaine createClient(ClientDomaine client);

    public List<ClientDomaine> getAllClients();

    ClientDomaine getClientById(Long clientId);
}
