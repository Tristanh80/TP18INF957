package com.reservation.uqac.domaine.reservation;

import com.reservation.uqac.domaine.hebergement.TypeChambre;

import java.util.List;

public interface ReservationRepository {
    List<ReservationDomaine> getAllReservation();

    List<ReservationDomaine> getReservationByHebergementId(Long id);

    List<ReservationDomaine> getReservationByHebergementIdAndChambreType(Long id, TypeChambre typeChambre);

    List<ReservationDomaine> getReservationByHebergementName(String name);

    ReservationDomaine doReservation(ReservationDomaine reservationDomaine);

    List<ReservationDomaine> getAllReservationDuringThisTime(ReservationDomaine reservationDomaine);

    List<ReservationDomaine> getReservationByClientId(Long id);

    void deleteReservation(ReservationDomaine reservationDomaine);
}
