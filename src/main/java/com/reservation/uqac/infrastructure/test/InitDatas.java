package com.reservation.uqac.infrastructure.test;

import com.github.javafaker.Faker;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.hebergement.TypeHebergement;
import com.reservation.uqac.infrastructure.client.ClientInfra;
import com.reservation.uqac.infrastructure.client.ClientRepositoryJPA;
import com.reservation.uqac.infrastructure.hebergement.HebergementInfra;
import com.reservation.uqac.infrastructure.hebergement.HebergementRepositoryJPA;
import com.reservation.uqac.infrastructure.region.AdresseInfra;
import com.reservation.uqac.infrastructure.region.AdresseRepositoryJPA;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InitDatas {

    private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InitDatas.class);

    private final AdresseRepositoryJPA adresseRepositoryJPA;

    private final HebergementRepositoryJPA hebergementRepositoryJPA;

    private final ClientRepositoryJPA clientRepositoryJPA;

    private final Faker faker;

    @Autowired
    public InitDatas(AdresseRepositoryJPA adresseRepositoryJPA, HebergementRepositoryJPA hebergementRepositoryJPA,
                     ClientRepositoryJPA clientRepositoryJPA) {
        this.adresseRepositoryJPA = adresseRepositoryJPA;
        this.hebergementRepositoryJPA = hebergementRepositoryJPA;
        this.clientRepositoryJPA = clientRepositoryJPA;
        this.faker = new Faker();
    }
    public void initAdress() {
        LOGGER.info("Init adress");
        List<String> nomQuartiers = List.of("Saint-Roch", "Saint-Jean-Baptiste", "Saint-Sauveur", "Saint-Sacrement", "Montcalm", "Vieux-Québec");
        List<AdresseInfra> adresseInfras = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AdresseInfra adresse = new AdresseInfra();
            adresse.setNumeroRue(faker.number().numberBetween(1, 1000));
            adresse.setNomRue(faker.address().streetName());
            adresse.setNomQuartier(nomQuartiers.get(faker.number().numberBetween(0, nomQuartiers.size())));
            adresse.setNomVille(faker.address().city());
            adresse.setNomPays(faker.address().country());
            adresseInfras.add(adresse);
        }
        adresseRepositoryJPA.saveAll(adresseInfras);
    }

    public AdresseInfra generateRandomAdresse() {
        AdresseInfra adresse = new AdresseInfra();
        adresse.setNumeroRue(faker.number().numberBetween(1, 1000));
        adresse.setNomRue(faker.address().streetName());
        adresse.setNomQuartier(faker.address().cityName());
        adresse.setNomVille(faker.address().city());
        adresse.setNomProvince(faker.address().state());
        adresse.setNomPays(faker.address().country());
        return adresse;
    }

    public void initHebergement(Integer nbHebergement) {
        LOGGER.info("Init random hebergement");
        List<HebergementInfra> hebergementInfras = new ArrayList<>();
        Set<TypeChambre> typesChambres = Set.of(TypeChambre.SIMPLE, TypeChambre.DOUBLE, TypeChambre.SUITE);
        for (int i = 0; i < nbHebergement; i++) {
            HebergementInfra hebergementInfra = new HebergementInfra();
            hebergementInfra.setNomHebergement(faker.company().name());
            hebergementInfra.setType(faker.options().option(TypeHebergement.class));
            hebergementInfra.setAdresseInfra(generateRandomAdresse());
            Integer random = faker.number().numberBetween(1, 4);
            Set<TypeChambre> result = new HashSet<>();
            while (result.size() < random) {
                typesChambres.stream()
                        .skip(faker.number().numberBetween(0, typesChambres.size()))
                        .findFirst().ifPresent(result::add);

            }
            hebergementInfra.setTypesChambres(result);
            if (result.contains(TypeChambre.SIMPLE)) {
                hebergementInfra.setNombreChambresSimple(faker.number().numberBetween(1, 10));
                hebergementInfra.setPrixChambreSimple((float) faker.number().numberBetween(50, 100));
            }
            if (result.contains(TypeChambre.DOUBLE)) {
                hebergementInfra.setNombreChambresDouble(faker.number().numberBetween(1, 10));
                hebergementInfra.setPrixChambreDouble((float) faker.number().numberBetween(50, 100));
            }
            if (result.contains(TypeChambre.SUITE)) {
                hebergementInfra.setNombreChambresSuite(faker.number().numberBetween(1, 10));
                hebergementInfra.setPrixChambreSuite((float) faker.number().numberBetween(50, 100));
            }

            Set<com.reservation.uqac.domaine.hebergement.Service> services = EnumSet.allOf(com.reservation.uqac.domaine.hebergement.Service.class);
            Set<com.reservation.uqac.domaine.hebergement.Service> resultServices = new HashSet<>();
            while (resultServices.size() < faker.number().numberBetween(1, services.size())) {
                services.stream()
                        .skip(faker.number().numberBetween(0, services.size()))
                        .findFirst()
                        .ifPresent(resultServices::add);
            }
            hebergementInfra.setServices(resultServices);
            hebergementInfras.add(hebergementInfra);
        }
        hebergementRepositoryJPA.saveAll(hebergementInfras);
        for (HebergementInfra hebergementInfra : hebergementInfras) {
            LOGGER.info(hebergementInfra.toString());
        }
        LOGGER.info("End init random hebergement");
    }

    public void initClients(Integer nbClients) {
        LOGGER.info("Init random clients");
        List<ClientInfra> clientInfras = new ArrayList<>();
        for (int i = 0; i < nbClients; i++) {
            ClientInfra clientInfra = new ClientInfra();
            clientInfra.setNom(faker.name().lastName());
            clientInfra.setPrenom(faker.name().firstName());
            clientInfra.setEmail(faker.internet().emailAddress());
            clientInfra.setTelephone(faker.phoneNumber().phoneNumber());
            clientInfra.setAdresse(generateRandomAdresse());
            clientInfras.add(clientInfra);
        }
        clientRepositoryJPA.saveAll(clientInfras);
        for (ClientInfra clientInfra : clientInfras) {
            LOGGER.info(clientInfra.toString());
        }
        LOGGER.info("End init random clients");
    }
}
