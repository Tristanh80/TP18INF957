package com.reservation.uqac.domaine.client;

import java.util.List;

public interface ClientRepository {
    public ClientDomaine getClientByNomAndPrenom(String nom, String prenom);

    public ClientDomaine getClientById(Long id);

    public ClientDomaine getClientByEmail(String email);

    public ClientDomaine getClientByTelephone(String telephone);

    public ClientDomaine createClient(ClientDomaine clientDomaine);

    public List<ClientDomaine> getAllClients();
}
