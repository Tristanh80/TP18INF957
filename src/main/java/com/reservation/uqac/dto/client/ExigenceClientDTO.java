package com.reservation.uqac.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private Float prixMax;

    @Override
    public String toString() {
        return "ExigenceClientDTO : " + "adresseAsking=" + adresseAsking + ", typeHebergement=" + typeHebergement +
                ", typeChambre=" + typeChambre + ", services=" + services + ", dateDebut=" + dateDebut + ", dateFin" +
                "=" + dateFin + ", prixMax=" + prixMax + "\n";
    }
}
