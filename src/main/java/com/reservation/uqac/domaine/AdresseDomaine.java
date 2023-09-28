package com.reservation.uqac.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdresseDomaine {
    private Integer numeroRue;
    private String rue;
    private String quartier;
    private String ville;
    private String province;
    private String pays;

    public String toString() {
        return numeroRue + "," + rue + "," + quartier + "," + ville + "," + province + "," + pays;
    }
}
