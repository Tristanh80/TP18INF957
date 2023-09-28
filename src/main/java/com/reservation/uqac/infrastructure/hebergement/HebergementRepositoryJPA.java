package com.reservation.uqac.infrastructure.hebergement;

import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.hebergement.TypeHebergement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HebergementRepositoryJPA extends JpaRepository<HebergementInfra, Long> {
    List<HebergementInfra> findByType(TypeHebergement typeHebergement);

    HebergementInfra findByNomHebergement(String nom);
}
