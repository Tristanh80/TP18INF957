package com.reservation.uqac.infrastructure.reservation;

import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.reservation.ReservationDomaine;
import com.reservation.uqac.infrastructure.client.ClientInfra;
import com.reservation.uqac.infrastructure.client.MapperClientInfraToClientDomaine;
import com.reservation.uqac.infrastructure.hebergement.HebergementInfra;
import com.reservation.uqac.infrastructure.hebergement.MapperHebergementInfraToHebergementDomaine;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class MapperReservationInfraToReservationDomaine {

    private MapperReservationInfraToReservationDomaine() {
        throw new IllegalStateException("Utility class");
    }

    public static ReservationDomaine mapToDomaine(ReservationInfra reservationInfra) {
        if (reservationInfra == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateDebut = format.format(reservationInfra.getDateDebut());
        String dateFin = format.format(reservationInfra.getDateFin());
        return new ReservationDomaine(
                reservationInfra.getId(),
                dateDebut,
                dateFin,
                reservationInfra.getHebergementInfra().getId(),
                reservationInfra.getTypeChambre().toString(),
                reservationInfra.getClientInfra().getId(),
                reservationInfra.getPrice()
        );
    }

    public static List<ReservationDomaine> mapToDomaine(List<ReservationInfra> reservationInfraList) {
        if (reservationInfraList == null) {
            return null;
        }
        return reservationInfraList.stream().map(MapperReservationInfraToReservationDomaine::mapToDomaine).toList();
    }

    public static ReservationInfra mapToInfra(ReservationDomaine reservationDomaine,
                                              HebergementInfra hebergementInfra, ClientInfra clientInfra) {
        if (reservationDomaine == null) {
            return null;
        }
        ReservationInfra reservationInfra = new ReservationInfra();
        /*Convert string date to Date sql*/
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date parsed = format.parse(reservationDomaine.getDateDebut());
            reservationInfra.setDateDebut(new java.sql.Date(parsed.getTime()));
        } catch (Exception e) {
            System.err.println("Error parsing date debut");
        }
        try {
            java.util.Date parsed = format.parse(reservationDomaine.getDateFin());
            reservationInfra.setDateFin(new java.sql.Date(parsed.getTime()));
        } catch (Exception e) {
            System.err.println("Error parsing date fin");
        }
        reservationInfra.setHebergementInfra(hebergementInfra);
        try {
            TypeChambre typeChambre = TypeChambre.valueOf(reservationDomaine.getTypeChambre().toUpperCase());
            reservationInfra.setTypeChambre(typeChambre);
            switch (typeChambre) {
                case SIMPLE -> reservationInfra.setPrice(hebergementInfra.getPrixChambreSimple());
                case DOUBLE -> reservationInfra.setPrice(hebergementInfra.getPrixChambreDouble());
                case SUITE -> reservationInfra.setPrice(hebergementInfra.getPrixChambreSuite());
            }

        } catch (Exception e) {
            System.err.println("Error parsing type chambre");
            return null;
        }
        reservationInfra.setClientInfra(clientInfra);
        return reservationInfra;
    }
}
