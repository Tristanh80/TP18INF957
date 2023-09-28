package com.reservation.uqac.application.reservation;

import com.reservation.uqac.application.mapper.MapperReservationDomaineAndReservationDTO;
import com.reservation.uqac.business.ClientInterface;
import com.reservation.uqac.business.HebergementInterface;
import com.reservation.uqac.business.SystemeGestionInterface;
import com.reservation.uqac.domaine.client.ClientDomaine;
import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.domaine.reservation.ReservationDomaine;
import com.reservation.uqac.dto.reservation.ReservationDTORequest;
import com.reservation.uqac.dto.reservation.ReservationDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(ReservationApplication.class);

    private final SystemeGestionInterface systemeGestionInterface;

    private final HebergementInterface hebergementInterface;

    private final ClientInterface clientInterface;

    @Autowired
    public ReservationApplication(SystemeGestionInterface systemeGestionInterface,
                                  HebergementInterface hebergementInterface,
                                  ClientInterface clientInterface) {
        this.systemeGestionInterface = systemeGestionInterface;
        this.hebergementInterface = hebergementInterface;
        this.clientInterface = clientInterface;
    }

    @Operation(summary = "Get all reservation")
    @GetMapping("")
    public ResponseEntity<List<ReservationDomaine>> getAllReservation() {
        return ResponseEntity.ok(systemeGestionInterface.getAllReservation());
    }

    @Operation(summary = "Get reservation by hebergement id")
    @GetMapping("/hebergement/{id}")
    public ResponseEntity<List<ReservationDomaine>> getReservationByHebergementId(@PathVariable Long id) {
        return ResponseEntity.ok(systemeGestionInterface.getReservationByHebergementId(id));
    }

    @Operation(summary = "Get reservation by hebergement id and chambre type")
    @GetMapping("/hebergement/{id}/{chambre}")
    public ResponseEntity<List<ReservationDomaine>> getReservationByHebergementIdAndChambreType(@PathVariable Long id, @PathVariable String chambre) {
        return ResponseEntity.ok(systemeGestionInterface.getReservationByHebergementIdAndChambreType(id, chambre));
    }

    @Operation(summary = "Get reservation by hebergement name")
    @GetMapping("/hebergement/{name}")
    public ResponseEntity<List<ReservationDomaine>> getReservationByHebergementName(@PathVariable String name) {
        return ResponseEntity.ok(systemeGestionInterface.getReservationByHebergementName(name));
    }

    @Operation(summary = "Get number of chambre available during this time")
    @PostMapping("/nbChambreAvailable")
    public ResponseEntity<Integer> getNbChambreAvailableDuringThisTime(@RequestBody ReservationDomaine reservationDomaine) {
        Integer nbChambreAvailable = systemeGestionInterface.getNbChambreAvailableDuringThisTime(reservationDomaine);
        if (nbChambreAvailable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nbChambreAvailable);
    }


    @Operation(summary = "After checking the availability of the accommodation, the client can make a reservation")
    @PostMapping("")
    public ResponseEntity<ReservationDTOResponse> doReservation(@RequestBody ReservationDTORequest reservationDTORequest) {
        ReservationDomaine reservationDomaine = MapperReservationDomaineAndReservationDTO.mapReservationDTOToReservationDomaine(reservationDTORequest);
        HebergementDomaine hebergementDomaine = hebergementInterface.getHebergementById(reservationDomaine.getHebergementId());
        if (hebergementDomaine == null) {
            System.err.println("Hebergement not found");
            return ResponseEntity.notFound().build();
        }
        ClientDomaine clientDomaine = clientInterface.getClientById(reservationDomaine.getClientId());
        if (clientDomaine == null) {
            System.err.println("Client not found");
            return ResponseEntity.notFound().build();
        }
        ReservationDomaine reservationDomaine1 = systemeGestionInterface.doReservation(reservationDomaine,
                reservationDTORequest.getPrix());
        if (reservationDomaine1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MapperReservationDomaineAndReservationDTO.mapReservationDomaineToReservationDTOResponse(reservationDomaine1));
    }

    @Operation(summary = "Get reservation by client id")
    @GetMapping("/client/{id}")
    public ResponseEntity<List<ReservationDomaine>> getReservationByClientId(@PathVariable Long id) {
        return ResponseEntity.ok(systemeGestionInterface.getReservationByClientId(id));
    }

    @Operation(summary = "Delete reservation by client id")
    @DeleteMapping("/client/{id}/{idReservation}")
    public ResponseEntity<Void> deleteReservationByClientId(@PathVariable Long id, @PathVariable Long idReservation) {
        List<ReservationDomaine> reservationDomaineList = systemeGestionInterface.getReservationByClientId(id);
        if (reservationDomaineList == null) {
            return ResponseEntity.notFound().build();
        }
        for (ReservationDomaine reservationDomaine : reservationDomaineList) {
            if (reservationDomaine.getId().equals(idReservation)) {
                systemeGestionInterface.deleteReservation(reservationDomaine);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}

