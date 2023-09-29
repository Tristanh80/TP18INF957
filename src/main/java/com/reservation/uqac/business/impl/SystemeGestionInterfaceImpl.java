package com.reservation.uqac.business.impl;

import com.reservation.uqac.business.SystemeGestionInterface;
import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.domaine.hebergement.HebergementRepository;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.reservation.ReservationDomaine;
import com.reservation.uqac.domaine.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

    /*
        * Retourne le nombre de chambre disponible pour une reservation en fonction des criteres du client
        * L'entree est de la bonne forme
     */
    private Integer getNumberOfChambresAvailable(ReservationDomaine reservationDomaineEnCoursDeDemande,
                                                  List<ReservationDomaine> listeDesReservationExistantes,
                                                        Integer nbChambres) {
        Date dateDebutReservationEnCoursDeDemande;
        Date dateFinReservationEnCoursDeDemande;
        Date dateDebutReservationExistant;
        Date dateFinReservationExistant;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dateDebutReservationEnCoursDeDemande =
                    new Date(simpleDateFormat.parse(reservationDomaineEnCoursDeDemande.getDateDebut()).getTime());
            dateFinReservationEnCoursDeDemande =
                    new Date(simpleDateFormat.parse(reservationDomaineEnCoursDeDemande.getDateFin()).getTime());
        } catch (Exception e) {
            return null;
        }
        for (ReservationDomaine reservationDomaineExistant : listeDesReservationExistantes) {
            try {
                dateDebutReservationExistant =
                        new Date(simpleDateFormat.parse(reservationDomaineExistant.getDateDebut()).getTime());
                dateFinReservationExistant =
                        new Date(simpleDateFormat.parse(reservationDomaineExistant.getDateFin()).getTime());
            } catch (Exception e) {
                return null;
            }
            if (dateDebutReservationEnCoursDeDemande.before(dateFinReservationExistant)
                    && dateFinReservationEnCoursDeDemande.after(dateDebutReservationExistant)) {
                nbChambres--;
            }
        }
        return nbChambres;
    }

    /*
        * Filtre la liste des reservations en fonction du type de chambre
        * Dispatche la requete en fonction du type de chambre
     */
    private Integer getNumberOfRoomsAvailableDispatcher(ReservationDomaine reservationDomaineEnCoursDeDemande,
                                                        List<ReservationDomaine> reservationDomaineList
                                                        ) {
        switch (reservationDomaineEnCoursDeDemande.getTypeChambre().toLowerCase()) {
            case "simple" -> {

                if (reservationDomaineList != null && !reservationDomaineList.isEmpty()) {
                    reservationDomaineList = reservationDomaineList.stream().filter(reservationDomaine -> reservationDomaine.getTypeChambre().equals(
                            "SIMPLE")).toList();
                }
                return getNumberOfChambresAvailable(reservationDomaineEnCoursDeDemande, reservationDomaineList,
                        hebergementRepository.getHebergementById(reservationDomaineEnCoursDeDemande.getHebergementId()).getNombreChambresSimple());
            }
            case "double" -> {
                if (reservationDomaineList != null && !reservationDomaineList.isEmpty()) {
                    reservationDomaineList = reservationDomaineList.stream().filter(reservationDomaine -> reservationDomaine.getTypeChambre().equals(
                            "DOUBLE")).toList();
                }
                return getNumberOfChambresAvailable(reservationDomaineEnCoursDeDemande, reservationDomaineList,
                        hebergementRepository.getHebergementById(reservationDomaineEnCoursDeDemande.getHebergementId()).getNombreChambresDouble());
            }
            case "suite" -> {
                if (reservationDomaineList != null && !reservationDomaineList.isEmpty()) {
                    reservationDomaineList = reservationDomaineList.stream().filter(reservationDomaine -> reservationDomaine.getTypeChambre().equals(
                            "SUITE")).toList();
                }
                return getNumberOfChambresAvailable(reservationDomaineEnCoursDeDemande, reservationDomaineList,
                        hebergementRepository.getHebergementById(reservationDomaineEnCoursDeDemande.getHebergementId()).getNombreChambresSuite());
            }
            default -> {
                return null;
            }
        }
    }

    /*
        * Retourne le nombre de chambre disponible pour une reservation en fonction des criteres du client
        * Retourne null si l'hebergement n'existe pas
        * Retourne null si le type de chambre n'existe pas
     */
    @Override
    public Integer getNbChambreAvailableDuringThisTime(ReservationDomaine reservationDomaine) {
        HebergementDomaine hebergementDomaine = hebergementRepository.getHebergementById(reservationDomaine.getHebergementId());
        if (hebergementDomaine == null) {
            return null;
        }
        TypeChambre typeChambre;
        try {
            typeChambre = TypeChambre.valueOf(reservationDomaine.getTypeChambre().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Type chambre not found");
            return null;
        }
        List<ReservationDomaine> reservationDomaineList =
                reservationRepository.getReservationByHebergementId(reservationDomaine.getHebergementId());

        return getNumberOfRoomsAvailableDispatcher(reservationDomaine, reservationDomaineList);
    }

    /*
    * Effectue une reservation si les criteres sont respectes
     */
    @Override
    public ReservationDomaine doReservation(ReservationDomaine reservationDomaine, float price) {
        HebergementDomaine hebergementDomaine = hebergementRepository.getHebergementById(reservationDomaine.getHebergementId());
        Integer nbChambreAvailable = getNbChambreAvailableDuringThisTime(reservationDomaine);
        if (nbChambreAvailable == null) {
            System.err.println("Hebergement not found");
            return null;
        }

        if (nbChambreAvailable == 0) {
            System.err.println("No chambre available");
            return null;
        }

        List<ReservationDomaine> reservationDomaineList = reservationRepository.getAllReservationDuringThisTime(reservationDomaine);
        int nbChambre = 0;
        float prix = 0;

        // Recuperaion du nombre de chambre et du prix
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
