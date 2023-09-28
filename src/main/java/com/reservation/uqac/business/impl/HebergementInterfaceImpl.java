package com.reservation.uqac.business.impl;

import com.reservation.uqac.business.HebergementInterface;
import com.reservation.uqac.domaine.hebergement.ExigenceClient;
import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.domaine.hebergement.HebergementRepository;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.dto.client.ExigenceClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class HebergementInterfaceImpl implements HebergementInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(HebergementInterfaceImpl.class);

    private final HebergementRepository hebergementRepository;

    @Autowired
    public HebergementInterfaceImpl(HebergementRepository hebergementRepository) {
        this.hebergementRepository = hebergementRepository;
    }

    private List<HebergementDomaine> getHebergementByType(String type) {
        if (type == null || type.isEmpty()) {
            return hebergementRepository.getAllHebergement();
        }
        return hebergementRepository.getHebergementByType(type);
    }

    private void getHebergementByPays(List<HebergementDomaine> hebergementDomaineList, String pays) {
        hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getAdresseHebergement().getPays().equals(pays));
    }

    private void getHebergementByProvince(List<HebergementDomaine> hebergementDomaineList, String province) {
        hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getAdresseHebergement().getProvince().equals(province));
    }

    private void getHebergementByVille(List<HebergementDomaine> hebergementDomaineList, String ville) {
        hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getAdresseHebergement().getVille().equals(ville));
    }

    private void getHebergementByQuartier(List<HebergementDomaine> hebergementDomaineList, String quartier) {
        hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getAdresseHebergement().getQuartier().equals(quartier));
    }

    private void getHebergementByRue(List<HebergementDomaine> hebergementDomaineList, String rue) {
        hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getAdresseHebergement().getRue().equals(rue));
    }

    private void getHebergementByNumero(List<HebergementDomaine> hebergementDomaineList,
                                        Integer numero) {
        hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getAdresseHebergement().getNumeroRue().equals(numero));
    }

    private void getByChambreType(List<HebergementDomaine> hebergementDomaineList, String type) {
        switch (type.toLowerCase()) {
            case "simple" ->
                    hebergementDomaineList.removeIf(hebergementDomaine -> hebergementDomaine.getNombreChambresSimple() == 0);
            case "double" ->
                    hebergementDomaineList.removeIf(hebergementDomaine -> hebergementDomaine.getNombreChambresDouble() == 0);
            case "suite" ->
                    hebergementDomaineList.removeIf(hebergementDomaine -> hebergementDomaine.getNombreChambresSuite() == 0);
        }
    }

    private void getByService(List<HebergementDomaine> hebergementDomaineList,
                              Set<com.reservation.uqac.domaine.hebergement.Service> service) {
        for (com.reservation.uqac.domaine.hebergement.Service service1 : service) {
            hebergementDomaineList.removeIf(hebergementDomaine -> !hebergementDomaine.getServices().contains(service1));
        }
    }

    private void removeToHighPrice(List<HebergementDomaine> hebergementDomaineList, float price, TypeChambre typeChambre) {
        switch (typeChambre) {
            case SIMPLE -> hebergementDomaineList.removeIf(hebergementDomaine -> hebergementDomaine.getPrixChambresSimple() > price);
            case DOUBLE -> hebergementDomaineList.removeIf(hebergementDomaine -> hebergementDomaine.getPrixChambresDouble() > price);
            case SUITE -> hebergementDomaineList.removeIf(hebergementDomaine -> hebergementDomaine.getPrixChambresSuite() > price);
        }
    }

    @Override
    public List<HebergementDomaine> getHebergementByCriteria(ExigenceClientDTO exigenceClientDTO) {
        LOGGER.info("Get hebergement by criteria");
        ExigenceClient exigenceClient = new ExigenceClient(exigenceClientDTO);
        if (exigenceClient.getTypeHebergement() == null
                || exigenceClient.getTypeHebergement().isEmpty()
                || exigenceClient.getAdresseAsking() == null) {
            return null;
        }
        LOGGER.info("Remove all hebergement not corresponding to criteria");
        List<HebergementDomaine> hebergementDomaineList = getHebergementByType(exigenceClient.getTypeHebergement());
        if (hebergementDomaineList == null || hebergementDomaineList.isEmpty()) {
            return null;
        }
        if (exigenceClient.getAdresseAsking() != null) {
            if (exigenceClient.getAdresseAsking().getPays() != null) {
                getHebergementByPays(hebergementDomaineList, exigenceClient.getAdresseAsking().getPays());
            }
            if (exigenceClient.getAdresseAsking().getProvince() != null) {
                getHebergementByProvince(hebergementDomaineList, exigenceClient.getAdresseAsking().getProvince());
            }
            if (exigenceClient.getAdresseAsking().getVille() != null) {
                getHebergementByVille(hebergementDomaineList, exigenceClient.getAdresseAsking().getVille());
            }
            if (exigenceClient.getAdresseAsking().getQuartier() != null) {
                getHebergementByQuartier(hebergementDomaineList, exigenceClient.getAdresseAsking().getQuartier());
            }
            if (exigenceClient.getAdresseAsking().getRue() != null) {
                getHebergementByRue(hebergementDomaineList, exigenceClient.getAdresseAsking().getRue());
            }
            if (exigenceClient.getAdresseAsking().getNumeroRue() != null) {
                getHebergementByNumero(hebergementDomaineList, exigenceClient.getAdresseAsking().getNumeroRue());
            }
        }
        if (exigenceClient.getTypeChambre() != null && !exigenceClient.getTypeChambre().isEmpty()) {
            try {
                TypeChambre typeChambre = TypeChambre.valueOf(exigenceClient.getTypeChambre().toUpperCase());
                getByChambreType(hebergementDomaineList, typeChambre.toString());
                removeToHighPrice(hebergementDomaineList, exigenceClientDTO.getPrixMax(), typeChambre);
            } catch (IllegalArgumentException e) {
                System.err.println("Type chambre not found");
                return null;
            }
        }
        if (exigenceClient.getServices() != null && !exigenceClient.getServices().isEmpty()) {
            getByService(hebergementDomaineList, exigenceClient.getServices());
        }
        return hebergementDomaineList;
    }

    @Override
    public List<HebergementDomaine> getAllHebergement() {
        return hebergementRepository.getAllHebergement();
    }

    @Override
    public HebergementDomaine getHebergementById(Long hebergementId) {
        return hebergementRepository.getHebergementById(hebergementId);
    }
}
