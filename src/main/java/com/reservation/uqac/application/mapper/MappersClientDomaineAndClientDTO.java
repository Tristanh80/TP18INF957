package com.reservation.uqac.application.mapper;

import com.reservation.uqac.domaine.mapper.MapperAdresseFromString;
import com.reservation.uqac.domaine.client.ClientDomaine;
import com.reservation.uqac.dto.client.ClientDTO;

public class MappersClientDomaineAndClientDTO {

    private MappersClientDomaineAndClientDTO() {
        throw new IllegalStateException("Utility class");
    }

    public static ClientDomaine mapClientDTOToClientDomaine(ClientDTO clientDTO) {
        ClientDomaine clientDomaine = new ClientDomaine();
        clientDomaine.setNom(clientDTO.getNom());
        clientDomaine.setPrenom(clientDTO.getPrenom());
        clientDomaine.setEmail(clientDTO.getEmail());
        clientDomaine.setTelephone(clientDTO.getTelephone());
        clientDomaine.setAdresse(MapperAdresseFromString.mapFromString(clientDTO.getAdresse()));
        return clientDomaine;
    }

    public static ClientDTO mapClientDomaineToClientDTO(ClientDomaine clientDomaine) {
        if (clientDomaine == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNom(clientDomaine.getNom());
        clientDTO.setPrenom(clientDomaine.getPrenom());
        clientDTO.setEmail(clientDomaine.getEmail());
        clientDTO.setTelephone(clientDomaine.getTelephone());
        clientDTO.setAdresse(clientDomaine.getAdresse().toString());
        return clientDTO;
    }
}
