package com.reservation.uqac.infrastructure.hebergement;

import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.domaine.hebergement.Service;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.hebergement.TypeHebergement;
import com.reservation.uqac.infrastructure.region.MapperAdresseDomaineToAdresseInfra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MapperHebergementInfraToHebergementDomaine {

    private MapperHebergementInfraToHebergementDomaine() {
        throw new IllegalStateException("Utility class");
    }

    public static HebergementInfra mapToHebergementInfra(HebergementDomaine hebergementDomaine) {
        HebergementInfra hebergementInfra = new HebergementInfra();
        hebergementInfra.setId(hebergementDomaine.getId());
        TypeHebergement typeHebergement = TypeHebergement.valueOf(hebergementDomaine.getTypeHebergement().toUpperCase());
        hebergementInfra.setType(typeHebergement);
        hebergementInfra.setNomHebergement(hebergementDomaine.getNomHebergement());
        hebergementInfra.setAdresseInfra(
                MapperAdresseDomaineToAdresseInfra.mapToAdresseInfra(
                        hebergementDomaine.getAdresseHebergement())
        );
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static HebergementDomaine mapToHebergementDomaine(HebergementInfra hebergementInfra) {
        if (hebergementInfra == null) {
            return null;
        }
        HebergementDomaine hebergementDomaine = new HebergementDomaine();
        hebergementDomaine.setId(hebergementInfra.getId());
        hebergementDomaine.setNomHebergement(hebergementInfra.getNomHebergement());
        hebergementDomaine.setAdresseHebergement(
                MapperAdresseDomaineToAdresseInfra.mapToAdresseDomaine(
                        hebergementInfra.getAdresseInfra())
        );
        hebergementDomaine.setTypeHebergement(hebergementInfra.getType().toString());
        Integer nombreChambresSimple = hebergementInfra.getNombreChambresSimple();
        hebergementDomaine.setNombreChambresSimple(Objects.requireNonNullElse(nombreChambresSimple, 0));
        Float prixChambresSimple = hebergementInfra.getPrixChambreSimple();
        hebergementDomaine.setPrixChambresSimple(Objects.requireNonNullElse(prixChambresSimple, 0f));


        Integer nombreChambresDouble = hebergementInfra.getNombreChambresDouble();
        hebergementDomaine.setNombreChambresDouble(Objects.requireNonNullElse(nombreChambresDouble, 0));
        Float prixChambresDouble = hebergementInfra.getPrixChambreDouble();
        hebergementDomaine.setPrixChambresDouble(Objects.requireNonNullElse(prixChambresDouble, 0f));

        Integer nombreChambresSuite = hebergementInfra.getNombreChambresSuite();
        hebergementDomaine.setNombreChambresSuite(Objects.requireNonNullElse(nombreChambresSuite, 0));
        Float prixChambresSuite = hebergementInfra.getPrixChambreSuite();
        hebergementDomaine.setPrixChambresSuite(Objects.requireNonNullElse(prixChambresSuite, 0f));
        Set<Service> services = new HashSet<>(hebergementInfra.getServices());
        hebergementDomaine.setServices(services);
        return hebergementDomaine;
    }

    public static List<HebergementDomaine> mapToHebergementDomaine(List<HebergementInfra> hebergementInfraList) {
        List<HebergementDomaine> hebergementDomaineList = new ArrayList<>();
        for (HebergementInfra hebergementInfra : hebergementInfraList) {
            hebergementDomaineList.add(mapToHebergementDomaine(hebergementInfra));
        }
        return hebergementDomaineList;
    }
}
