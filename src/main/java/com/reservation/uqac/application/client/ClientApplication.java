package com.reservation.uqac.application.client;

import com.reservation.uqac.application.mapper.MappersClientDomaineAndClientDTO;
import com.reservation.uqac.business.ClientInterface;
import com.reservation.uqac.domaine.client.ClientDomaine;
import com.reservation.uqac.dto.client.ClientDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientApplication {

    private final ClientInterface clientInterface;

    @Autowired
    public ClientApplication(ClientInterface clientInterface) {
        this.clientInterface = clientInterface;
    }

    @Operation(summary = "Get all clients")
    @GetMapping("/get")
    public ResponseEntity<List<ClientDomaine>> getClient() {
        List<ClientDomaine> clientDomaineList = clientInterface.getAllClients();
        if (clientDomaineList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDomaineList);
    }

    @Operation(summary = "Create a client")
    @PostMapping("/create")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDomaine clientDomaine = MappersClientDomaineAndClientDTO.mapClientDTOToClientDomaine(clientDTO);
        ClientDomaine clientDomaineResponse = clientInterface.createClient(clientDomaine);
        if (clientDomaineResponse == null) {
            return ResponseEntity.notFound().build();
        }
        ClientDTO response = MappersClientDomaineAndClientDTO.mapClientDomaineToClientDTO(clientDomaineResponse);
        return ResponseEntity.ok(response);
    }


}
