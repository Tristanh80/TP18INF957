package com.reservation.uqac.business.impl;

import com.reservation.uqac.business.SystemeGestionInterface;
import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.domaine.hebergement.HebergementRepository;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.reservation.ReservationDomaine;
import com.reservation.uqac.domaine.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemeGestionInterfaceImpl implements SystemeGestionInterface {

    private final ReservationRepository reservationRepository;

    private final HebergementRepository hebergementRepository;

    @Autowired
    public SystemeGestionInterfaceImpl(ReservationRepository reservationRepository, HebergementRepository hebergementRepository) {
        this.reservationRepository = reservationRepository;
        this.hebergementRepository = hebergementRepository;
    }

    @Override
    public List<ReservationDomaine> getAllReservation() {
        return reservationRepository.getAllReservation();
    }

    @Override
    public List<ReservationDomaine> getReservationByHebergementId(Long id) {
        return reservationRepository.getReservationByHebergementId(id);
    }

    @Override
    public List<ReservationDomaine> getReservationByHebergementIdAndChambreType(Long id, String chambre) {
        try {
            TypeChambre typeChambre = TypeChambre.valueOf(chambre.toUpperCase());
            return reservationRepository.getReservationByHebergementIdAndChambreType(id, typeChambre);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public List<ReservationDomaine> getReservationByHebergementName(String name) {
        return reservationRepository.getReservationByHebergementName(name);
    }

    @Override
    public Integer getNbChambreAvailableDuringThisTime(ReservationDomaine reservationDomaine) {
        HebergementDomaine hebergementDomaine = hebergementRepository.getHebergementById(reservationDomaine.getHebergementId());
        if (hebergementDomaine == null) {
            return null;
        }
        List<ReservationDomaine> reservationDomaineList = reservationRepository.getAllReservationDuringThisTime(reservationDomaine);
        switch (reservationDomaine.getTypeChambre().toLowerCase()) {
            case "simple" -> {
                return hebergementDomaine.getNombreChambresSimple() - reservationDomaineList.size();
            }
            case "double" -> {
                return hebergementDomaine.getNombreChambresDouble() - reservationDomaineList.size();
            }
            case "suite" -> {
                return hebergementDomaine.getNombreChambresSuite() - reservationDomaineList.size();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public ReservationDomaine doReservation(ReservationDomaine reservationDomaine, float price) {
        HebergementDomaine hebergementDomaine = hebergementRepository.getHebergementById(reservationDomaine.getHebergementId());

        List<ReservationDomaine> reservationDomaineList = reservationRepository.getAllReservationDuringThisTime(reservationDomaine);
        int nbChambre = 0;
        float prix = 0;
        switch (reservationDomaine.getTypeChambre().toLowerCase()) {
            case "simple" -> {
                nbChambre = hebergementDomaine.getNombreChambresSimple();
                if (nbChambre == 0) {
                    System.err.println("No chambre simple");
                    return null;
                }
                prix = hebergementDomaine.getPrixChambresSimple();
            }
            case "double" -> {
                nbChambre = hebergementDomaine.getNombreChambresDouble();
                if (nbChambre == 0) {
                    System.err.println("No chambre double");
                    return null;
                }
                prix = hebergementDomaine.getPrixChambresDouble();
            }
            case "suite" -> {
                nbChambre = hebergementDomaine.getNombreChambresSuite();
                if (nbChambre == 0) {
                    System.err.println("No chambre suite");
                    return null;
                }
                prix = hebergementDomaine.getPrixChambresSuite();
            }
            default -> {
                return null;
            }
        }
        if (reservationDomaineList.size() >= nbChambre) {
            System.err.println("No chambre available");
            return null;
        }
        if (price <= prix) {
            System.err.println("Price too low");
            return null;
        }
        return reservationRepository.doReservation(reservationDomaine);
    }

    @Override
    public List<ReservationDomaine> getReservationByClientId(Long id) {
        return reservationRepository.getReservationByClientId(id);
    }

    @Override
    public void deleteReservation(ReservationDomaine reservationDomaine) {
        reservationRepository.deleteReservation(reservationDomaine);
    }
}
