package com.reservation.uqac.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExigenceClientDTO {
    private String adresseAsking;
    private String typeHebergement;
    private String typeChambre;
    private String services;
    private Date dateDebut;
    private Date dateFin;
    private Float prixMax;

    @Override
    public String toString() {
        return "ExigenceClientDTO : " + "adresseAsking=" + adresseAsking + ", typeHebergement=" + typeHebergement +
                ", typeChambre=" + typeChambre + ", services=" + services + ", dateDebut=" + dateDebut + ", dateFin" +
                "=" + dateFin + ", prixMax=" + prixMax + "\n";
    }
}
