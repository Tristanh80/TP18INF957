package com.reservation.uqac.domaine.hebergement;

import com.reservation.uqac.domaine.mapper.MapperAdresseFromString;
import com.reservation.uqac.domaine.AdresseDomaine;
import com.reservation.uqac.domaine.mapper.MapperServiceFromString;
import com.reservation.uqac.dto.client.ExigenceClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExigenceClient {
    private AdresseDomaine adresseAsking;
    private String typeHebergement;
    private String typeChambre;
    private Set<Service> services;
    private float prixMax;

    public ExigenceClient(ExigenceClientDTO exigenceClientDTO) {
        this.adresseAsking = MapperAdresseFromString.mapFromString(exigenceClientDTO.getAdresseAsking());
        this.typeHebergement = exigenceClientDTO.getTypeHebergement();
        this.typeChambre = exigenceClientDTO.getTypeChambre();
        this.services = MapperServiceFromString.mapFromString(exigenceClientDTO.getServices());
        this.prixMax = exigenceClientDTO.getPrixMax();
    }
}
