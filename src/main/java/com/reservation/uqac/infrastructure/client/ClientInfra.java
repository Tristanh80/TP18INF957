package com.reservation.uqac.infrastructure.client;

import com.reservation.uqac.infrastructure.region.AdresseInfra;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ClientInfra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private AdresseInfra adresseInfra;

    public ClientInfra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AdresseInfra getAdresse() {
        return adresseInfra;
    }

    public void setAdresse(AdresseInfra adresseInfra) {
        this.adresseInfra = adresseInfra;
    }

    @Override
    public String toString() {
        return "ClientInfra : " + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
                + ", telephone=" + telephone + ", adresseInfra=" + adresseInfra;
    }
}
