package com.reservation.uqac.infrastructure.reservation;

import com.reservation.uqac.domaine.client.ClientRepository;
import com.reservation.uqac.domaine.hebergement.HebergementRepository;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.reservation.ReservationDomaine;
import com.reservation.uqac.domaine.reservation.ReservationRepository;
import com.reservation.uqac.infrastructure.client.ClientInfra;
import com.reservation.uqac.infrastructure.client.ClientRepositoryJPA;
import com.reservation.uqac.infrastructure.hebergement.HebergementInfra;
import com.reservation.uqac.infrastructure.hebergement.HebergementRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationRepositoryImpl implements ReservationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRepositoryImpl.class);

    private final ReservationRespositoryJPA reservationRespositoryJPA;

    private final HebergementRepositoryJPA hebergementRepositoryJPA;

    private final ClientRepositoryJPA clientRepositoryJPA;

    @Autowired
    public ReservationRepositoryImpl(
            ReservationRespositoryJPA reservationRespositoryJPA,
            HebergementRepositoryJPA hebergementRepositoryJPA,
            ClientRepositoryJPA clientRepositoryJPA
    ) {
        this.reservationRespositoryJPA = reservationRespositoryJPA;
        this.hebergementRepositoryJPA = hebergementRepositoryJPA;
        this.clientRepositoryJPA = clientRepositoryJPA;
    }
    @Override
    public List<ReservationDomaine> getAllReservation() {
        LOGGER.info("Get all reservation");
        List<ReservationInfra> reservationInfraList = reservationRespositoryJPA.findAll();
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfraList);
    }

    @Override
    public List<ReservationDomaine> getReservationByHebergementId(Long id) {
        LOGGER.info("Get reservation by hebergement id {}", id);
        List<ReservationInfra> reservationInfraList = reservationRespositoryJPA.findByHebergementInfraId(id);
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfraList);
    }

    @Override
    public List<ReservationDomaine> getReservationByHebergementIdAndChambreType(Long id, TypeChambre chambre) {
        LOGGER.info("Get reservation by hebergement id {} and chambre type {}", id, chambre);
        List<ReservationInfra> reservationInfraList = reservationRespositoryJPA.findByHebergementInfraIdAndTypeChambre(id, chambre);
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfraList);
    }

    @Override
    public List<ReservationDomaine> getReservationByHebergementName(String name) {
        LOGGER.info("Get reservation by hebergement name {}", name);
        HebergementInfra hebergementInfra = hebergementRepositoryJPA.findByNomHebergement(name);
        List<ReservationInfra> reservationInfras = reservationRespositoryJPA.findByHebergementInfra(hebergementInfra);
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfras);
    }

    @Override
    public ReservationDomaine doReservation(ReservationDomaine reservationDomaine) {
        LOGGER.info("Do reservation {}", reservationDomaine);
        Optional<ClientInfra> clientInfra = clientRepositoryJPA.findById(reservationDomaine.getClientId());
        if (clientInfra.isEmpty()) {
            return null;
        }
        Optional<HebergementInfra> hebergementInfra = hebergementRepositoryJPA.findById(reservationDomaine.getHebergementId());
        if (hebergementInfra.isEmpty()) {
            return null;
        }
        ReservationInfra reservationInfra = MapperReservationInfraToReservationDomaine.mapToInfra(reservationDomaine, hebergementInfra.get(), clientInfra.get());
        ReservationInfra reservationInfraResponse = reservationRespositoryJPA.save(reservationInfra);
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfraResponse);
    }

    @Override
    public List<ReservationDomaine> getAllReservationDuringThisTime(ReservationDomaine reservationDomaine) {
        LOGGER.info("Get all reservation during this time {}", reservationDomaine);
        TypeChambre typeChambre;
        try {
            typeChambre = TypeChambre.valueOf(reservationDomaine.getTypeChambre().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Type chambre not found");
            return null;
        }
        HebergementInfra hebergementInfra = hebergementRepositoryJPA.findById(reservationDomaine.getHebergementId()).orElse(null);
        if (hebergementInfra == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebut;
        Date dateFin;
        try {
            dateDebut = new Date(simpleDateFormat.parse(reservationDomaine.getDateDebut()).getTime());
            dateFin = new Date(simpleDateFormat.parse(reservationDomaine.getDateFin()).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<ReservationInfra> reservationInfraList = reservationRespositoryJPA.findByHebergementInfraAndTypeChambreAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
                hebergementInfra, typeChambre, dateDebut, dateFin);
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfraList);
    }

    @Override
    public List<ReservationDomaine> getReservationByClientId(Long id) {
        LOGGER.info("Get reservation by client id {}", id);
        List<ReservationInfra> reservationInfraList = reservationRespositoryJPA.findByClientInfraId(id);
        return MapperReservationInfraToReservationDomaine.mapToDomaine(reservationInfraList);
    }

    @Override
    public void deleteReservation(ReservationDomaine reservationDomaine) {
        LOGGER.info("Delete reservation {}", reservationDomaine);
        Optional<ReservationInfra> reservationInfra = reservationRespositoryJPA.findById(reservationDomaine.getId());
        if (reservationInfra.isEmpty()) {
            return;
        }
        reservationRespositoryJPA.delete(reservationInfra.get());
    }
}
