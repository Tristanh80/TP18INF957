package com.reservation.uqac.domaine.client;

import com.reservation.uqac.domaine.AdresseDomaine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDomaine {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private AdresseDomaine adresse;

    @Override
    public String toString() {
        return "ClientDomaine : " + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", " +
                "telephone=" +
                telephone + ", adresse=" + adresse + "\n";
    }
}
