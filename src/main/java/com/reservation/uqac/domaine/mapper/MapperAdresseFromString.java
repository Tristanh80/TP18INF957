package com.reservation.uqac.domaine.mapper;

import com.reservation.uqac.domaine.AdresseDomaine;

public class MapperAdresseFromString {

    private MapperAdresseFromString() {
        throw new IllegalStateException("Utility class");
    }

    public static AdresseDomaine mapFromString(String adresse) {
        AdresseDomaine adresseDomaine = new AdresseDomaine();
        String[] adresseSplit = adresse.split(",");
        for (int i = 0; i < adresseSplit.length; i++) {
            adresseSplit[i] = adresseSplit[i].trim();
/*
            System.out.println(i + adresseSplit[i]);
*/
        }
        if (adresseSplit[0] != null && adresseSplit[0].length() != 0) {
            try {
                Integer.parseInt(adresseSplit[0]);
            } catch (NumberFormatException e) {
                System.err.println("Le numero de rue n'est pas un nombre");
                return null;
            }
            adresseDomaine.setNumeroRue(Integer.parseInt(adresseSplit[0]));
        }
        if (adresseSplit[1] != null && adresseSplit[1].length() != 0) {
            adresseDomaine.setRue(adresseSplit[1]);
        }
        if (adresseSplit[2] != null && adresseSplit[2].length() != 0) {
            adresseDomaine.setQuartier(adresseSplit[2]);
        }
        if (adresseSplit[3] != null && adresseSplit[3].length() != 0) {
            adresseDomaine.setVille(adresseSplit[3]);
        }
        if (adresseSplit[4] != null && adresseSplit[4].length() != 0) {
            adresseDomaine.setProvince(adresseSplit[4]);
        }
        if (adresseSplit[5] != null && adresseSplit[5].length() != 0) {
            adresseDomaine.setPays(adresseSplit[5]);
        }
        return adresseDomaine;
    }
}
