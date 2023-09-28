package com.reservation.uqac.infrastructure.reservation;

import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.infrastructure.hebergement.HebergementInfra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRespositoryJPA extends JpaRepository<ReservationInfra, Long> {
    List<ReservationInfra> findByHebergementInfraId(Long id);

    List<ReservationInfra> findByHebergementInfra(HebergementInfra hebergementInfra);

    List<ReservationInfra> findByHebergementInfraIdAndTypeChambre(Long hebergementInfra_id, TypeChambre typeChambre);

    List<ReservationInfra> findByHebergementInfraAndTypeChambreAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            HebergementInfra hebergementInfra, TypeChambre typeChambre, Date dateDebut, Date dateFin);

    List<ReservationInfra> findByClientInfraId(Long id);
}
