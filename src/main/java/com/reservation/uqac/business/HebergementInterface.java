package com.reservation.uqac.business;

import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.dto.client.ExigenceClientDTO;

import java.util.List;

public interface HebergementInterface {

    List<HebergementDomaine> getAllHebergement();

    List<HebergementDomaine> getHebergementByCriteria(ExigenceClientDTO exigenceClient);

    HebergementDomaine getHebergementById(Long hebergementId);
}
