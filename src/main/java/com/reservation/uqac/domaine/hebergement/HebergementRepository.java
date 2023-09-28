package com.reservation.uqac.domaine.hebergement;

import java.util.List;

public interface HebergementRepository {

    List<HebergementDomaine> getAllHebergement();

    List<HebergementDomaine> getHebergementByType(String type);

    HebergementDomaine getHebergementById(Long id);

    HebergementDomaine getHebergementByAdresse(String adresse);
    HebergementDomaine getHebergementByCriteria(List<Service> services);

}
