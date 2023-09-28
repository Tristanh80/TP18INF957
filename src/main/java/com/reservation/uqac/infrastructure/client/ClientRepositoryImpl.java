package com.reservation.uqac.infrastructure.client;

import com.reservation.uqac.domaine.client.ClientDomaine;
import com.reservation.uqac.domaine.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientRepositoryJPA clientRepositoryJPA;

    @Autowired
    public ClientRepositoryImpl(ClientRepositoryJPA clientRepositoryJPA) {
        this.clientRepositoryJPA = clientRepositoryJPA;
    }

    @Override
    public ClientDomaine getClientByNomAndPrenom(String nom, String prenom) {
        ClientInfra clientInfra = clientRepositoryJPA.findByNomAndPrenom(nom, prenom);
        return MapperClientInfraToClientDomaine.mapToClientDomaine(clientInfra);
    }

    @Override
    public ClientDomaine getClientById(Long id) {
        ClientInfra clientInfra = clientRepositoryJPA.findById(id).orElse(null);
        if (clientInfra == null) {
            return null;
        }
        return MapperClientInfraToClientDomaine.mapToClientDomaine(clientInfra);
    }

    @Override
    public ClientDomaine getClientByEmail(String email) {
        ClientInfra clientInfra = clientRepositoryJPA.findByEmail(email);
        return MapperClientInfraToClientDomaine.mapToClientDomaine(clientInfra);
    }

    @Override
    public ClientDomaine getClientByTelephone(String telephone) {
        ClientInfra clientInfra = clientRepositoryJPA.findByTelephone(telephone);
        return MapperClientInfraToClientDomaine.mapToClientDomaine(clientInfra);
    }

    @Override
    public ClientDomaine createClient(ClientDomaine clientDomaine) {
        ClientInfra clientInfra = MapperClientInfraToClientDomaine.mapToClientInfra(clientDomaine);
        ClientInfra clientInfraResponse = clientRepositoryJPA.save(clientInfra);
        return MapperClientInfraToClientDomaine.mapToClientDomaine(clientInfraResponse);
    }

    @Override
    public List<ClientDomaine> getAllClients() {
        List<ClientInfra> clientInfraList = clientRepositoryJPA.findAll();
        return MapperClientInfraToClientDomaine.mapToClientDomaine(clientInfraList);
    }
}
