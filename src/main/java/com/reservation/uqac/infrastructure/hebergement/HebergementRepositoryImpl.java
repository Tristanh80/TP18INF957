package com.reservation.uqac.infrastructure.hebergement;

import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.domaine.hebergement.HebergementRepository;
import com.reservation.uqac.domaine.hebergement.TypeHebergement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HebergementRepositoryImpl implements HebergementRepository {

    private final HebergementRepositoryJPA hebergementRepositoryJPA;

    @Autowired
    public HebergementRepositoryImpl(HebergementRepositoryJPA hebergementRepositoryJPA) {
        this.hebergementRepositoryJPA = hebergementRepositoryJPA;
    }

    @Override
    public List<HebergementDomaine> getAllHebergement() {
        List<HebergementInfra> hebergementInfraList = hebergementRepositoryJPA.findAll();
        return MapperHebergementInfraToHebergementDomaine.mapToHebergementDomaine(hebergementInfraList);
    }

    @Override
    public List<HebergementDomaine> getHebergementByType(String type) {
        List<HebergementInfra> hebergementInfraList =
                hebergementRepositoryJPA.findByType(TypeHebergement.valueOf(type.toUpperCase()));
        return MapperHebergementInfraToHebergementDomaine.mapToHebergementDomaine(hebergementInfraList);
    }

    @Override
    public HebergementDomaine getHebergementById(Long id) {
        HebergementInfra hebergementInfra = hebergementRepositoryJPA.findById(id).orElse(null);
        return MapperHebergementInfraToHebergementDomaine.mapToHebergementDomaine(hebergementInfra);
    }

    @Override
    public HebergementDomaine getHebergementByAdresse(String adresse) {
        return null;
    }

    @Override
    public HebergementDomaine getHebergementByCriteria(List<com.reservation.uqac.domaine.hebergement.Service> services) {
        return null;
    }
}
