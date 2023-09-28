package com.reservation.uqac.application.mapper;

import com.reservation.uqac.domaine.reservation.ReservationDomaine;
import com.reservation.uqac.dto.reservation.ReservationDTORequest;
import com.reservation.uqac.dto.reservation.ReservationDTOResponse;

public class MapperReservationDomaineAndReservationDTO {

    private MapperReservationDomaineAndReservationDTO() {
        throw new IllegalStateException("Utility class");
    }

    public static ReservationDTORequest mapReservationDomaineToReservationDTO(ReservationDomaine reservationDomaine) {
        ReservationDTORequest reservationDTORequest = new ReservationDTORequest();
        reservationDTORequest.setDateDebut(reservationDomaine.getDateDebut());
        reservationDTORequest.setDateFin(reservationDomaine.getDateFin());
        reservationDTORequest.setHebergementId(reservationDomaine.getHebergementId());
        reservationDTORequest.setTypeChambre(reservationDomaine.getTypeChambre());
        reservationDTORequest.setClientId(reservationDomaine.getClientId());
        return reservationDTORequest;
    }

    public static ReservationDomaine mapReservationDTOToReservationDomaine(ReservationDTORequest reservationDTORequest) {
        ReservationDomaine reservationDomaine = new ReservationDomaine();
        reservationDomaine.setDateDebut(reservationDTORequest.getDateDebut());
        reservationDomaine.setDateFin(reservationDTORequest.getDateFin());
        reservationDomaine.setHebergementId(reservationDTORequest.getHebergementId());
        reservationDomaine.setTypeChambre(reservationDTORequest.getTypeChambre());
        reservationDomaine.setClientId(reservationDTORequest.getClientId());
        return reservationDomaine;
    }

    public static ReservationDTOResponse mapReservationDomaineToReservationDTOResponse(ReservationDomaine reservationDomaine) {
        ReservationDTOResponse reservationDTOResponse = new ReservationDTOResponse();
        reservationDTOResponse.setId(reservationDomaine.getId());
        reservationDTOResponse.setDateDebut(reservationDomaine.getDateDebut());
        reservationDTOResponse.setDateFin(reservationDomaine.getDateFin());
        reservationDTOResponse.setHebergementId(reservationDomaine.getHebergementId());
        reservationDTOResponse.setTypeChambre(reservationDomaine.getTypeChambre());
        reservationDTOResponse.setClientId(reservationDomaine.getClientId());
        reservationDTOResponse.setPrice(reservationDomaine.getPrice());
        return reservationDTOResponse;
    }
}
