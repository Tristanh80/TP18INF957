package com.reservation.uqac.infrastructure.region;

import com.reservation.uqac.domaine.AdresseDomaine;

public class MapperAdresseDomaineToAdresseInfra {

    private MapperAdresseDomaineToAdresseInfra() {
        throw new IllegalStateException("Utility class");
    }

    public static AdresseInfra mapToAdresseInfra(AdresseDomaine adresseDomaine) {
        AdresseInfra adresseInfra = new AdresseInfra();
        adresseInfra.setNumeroRue(adresseDomaine.getNumeroRue());
        adresseInfra.setNomRue(adresseDomaine.getRue());
        adresseInfra.setNomQuartier(adresseDomaine.getQuartier());
        adresseInfra.setNomVille(adresseDomaine.getVille());
        adresseInfra.setNomProvince(adresseDomaine.getProvince());
        adresseInfra.setNomPays(adresseDomaine.getPays());
        return adresseInfra;
    }

    public static AdresseDomaine mapToAdresseDomaine(AdresseInfra adresseInfra) {
        AdresseDomaine adresseDomaine = new AdresseDomaine();
        adresseDomaine.setNumeroRue(adresseInfra.getNumeroRue());
        adresseDomaine.setRue(adresseInfra.getNomRue());
        adresseDomaine.setQuartier(adresseInfra.getNomQuartier());
        adresseDomaine.setVille(adresseInfra.getNomVille());
        adresseDomaine.setProvince(adresseInfra.getNomProvince());
        adresseDomaine.setPays(adresseInfra.getNomPays());
        return adresseDomaine;
    }
}
