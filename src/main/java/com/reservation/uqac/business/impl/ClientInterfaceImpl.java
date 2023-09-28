package com.reservation.uqac.business.impl;

import com.reservation.uqac.business.ClientInterface;
import com.reservation.uqac.domaine.client.ClientDomaine;
import com.reservation.uqac.domaine.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientInterfaceImpl implements ClientInterface {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientInterfaceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public ClientDomaine getClient(ClientDomaine client) {
        if (client.getNom() != null && client.getPrenom() != null) {
            return clientRepository.getClientByNomAndPrenom(client.getNom(), client.getPrenom());
        } else if (client.getEmail() != null) {
            return clientRepository.getClientByEmail(client.getEmail());
        } else if (client.getTelephone() != null) {
            return clientRepository.getClientByTelephone(client.getTelephone());
        } else {
            return null;
        }
    }

    @Override
    public ClientDomaine createClient(ClientDomaine client) {
        return clientRepository.createClient(client);
    }

    @Override
    public List<ClientDomaine> getAllClients() {
        return clientRepository.getAllClients();
    }

    @Override
    public ClientDomaine getClientById(Long clientId) {
        return clientRepository.getClientById(clientId);
    }
}
