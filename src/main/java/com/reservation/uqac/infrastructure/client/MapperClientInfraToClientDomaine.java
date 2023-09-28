package com.reservation.uqac.infrastructure.client;

import com.reservation.uqac.domaine.client.ClientDomaine;
import com.reservation.uqac.infrastructure.region.MapperAdresseDomaineToAdresseInfra;

import java.util.ArrayList;
import java.util.List;

public class MapperClientInfraToClientDomaine {

    private MapperClientInfraToClientDomaine() {
        throw new IllegalStateException("Utility class");
    }

    public static ClientInfra mapToClientInfra(ClientDomaine clientDomaine) {
        if (clientDomaine == null) {
            return null;
        }
        ClientInfra clientInfra = new ClientInfra();
        clientInfra.setId(clientDomaine.getId());
        clientInfra.setNom(clientDomaine.getNom());
        clientInfra.setPrenom(clientDomaine.getPrenom());
        clientInfra.setEmail(clientDomaine.getEmail());
        clientInfra.setTelephone(clientDomaine.getTelephone());
        clientInfra.setAdresse(
                MapperAdresseDomaineToAdresseInfra.mapToAdresseInfra(
                        clientDomaine.getAdresse())
        );
        return clientInfra;
    }

    public static List<ClientInfra> mapToClientInfra(List<ClientDomaine> clientDomaineList) {
        if (clientDomaineList == null) {
            return null;
        }
        List<ClientInfra> clientInfraList = new ArrayList<>();
        for (ClientDomaine clientDomaine : clientDomaineList) {
            clientInfraList.add(mapToClientInfra(clientDomaine));
        }
        return clientInfraList;
    }

    public static ClientDomaine mapToClientDomaine(ClientInfra clientInfra) {
        if (clientInfra == null) {
            return null;
        }
        ClientDomaine clientDomaine = new ClientDomaine();
        clientDomaine.setId(clientInfra.getId());
        clientDomaine.setNom(clientInfra.getNom());
        clientDomaine.setPrenom(clientInfra.getPrenom());
        clientDomaine.setEmail(clientInfra.getEmail());
        clientDomaine.setTelephone(clientInfra.getTelephone());
        clientDomaine.setAdresse(
                MapperAdresseDomaineToAdresseInfra.mapToAdresseDomaine(
                        clientInfra.getAdresse())
        );
        return clientDomaine;
    }

    public static List<ClientDomaine> mapToClientDomaine(List<ClientInfra> clientInfraList) {
        if (clientInfraList == null) {
            return null;
        }
        List<ClientDomaine> clientDomaineList = new ArrayList<>();
        for (ClientInfra clientInfra : clientInfraList) {
            clientDomaineList.add(mapToClientDomaine(clientInfra));
        }
        return clientDomaineList;
    }
}
