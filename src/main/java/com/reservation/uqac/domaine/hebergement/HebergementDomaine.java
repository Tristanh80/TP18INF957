package com.reservation.uqac.domaine.hebergement;

import com.reservation.uqac.domaine.AdresseDomaine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HebergementDomaine {
    private Long id;
    private String nomHebergement;
    private AdresseDomaine adresseHebergement;
    private String typeHebergement;
    private Integer nombreChambresSimple;
    private Float prixChambresSimple;
    private Integer nombreChambresDouble;
    private Float prixChambresDouble;
    private Integer nombreChambresSuite;
    private Float prixChambresSuite;
    private Set<Service> services;

    @Override
    public String toString() {
        return "HebergementDomaine : " + "id=" + id + ", nomHebergement=" + nomHebergement + ", adresseHebergement=" +
                adresseHebergement + ", typeHebergement=" + typeHebergement + ", nombreChambresSimple=" +
                nombreChambresSimple + ", prixChambresSimple=" + prixChambresSimple + ", nombreChambresDouble=" +
                nombreChambresDouble + ", prixChambresDouble=" + prixChambresDouble + ", nombreChambresSuite=" +
                nombreChambresSuite + ", prixChambresSuite=" + prixChambresSuite + ", services=" + services + "\n";
    }
}
