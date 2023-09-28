package com.reservation.uqac.business;

import com.reservation.uqac.domaine.reservation.ReservationDomaine;

import java.util.List;

public interface SystemeGestionInterface {

    List<ReservationDomaine> getAllReservation();

    List<ReservationDomaine> getReservationByHebergementId(Long id);

    List<ReservationDomaine> getReservationByHebergementIdAndChambreType(Long id, String chambre);

    List<ReservationDomaine> getReservationByHebergementName(String name);

    Integer getNbChambreAvailableDuringThisTime(ReservationDomaine reservationDomaine);

    ReservationDomaine doReservation(ReservationDomaine reservationDomaine, float price);

    List<ReservationDomaine> getReservationByClientId(Long id);

    void deleteReservation(ReservationDomaine reservationDomaine);
}
