package com.reservation.uqac.infrastructure.region;

import com.reservation.uqac.infrastructure.hebergement.HebergementInfra;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AdresseInfra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne(mappedBy = "adresseInfra")
    private HebergementInfra hebergementInfra;

    private Integer numeroRue;

    private String nomRue;

    private String nomQuartier;

    private String nomVille;

    private String nomProvince;

    private String nomPays;

    public AdresseInfra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HebergementInfra getHebergementInfra() {
        return hebergementInfra;
    }

    public void setHebergementInfra(HebergementInfra hebergementInfra) {
        this.hebergementInfra = hebergementInfra;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getNomQuartier() {
        return nomQuartier;
    }

    public void setNomQuartier(String nomQuartier) {
        this.nomQuartier = nomQuartier;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getNomProvince() {
        return nomProvince;
    }

    public void setNomProvince(String nomProvince) {
        this.nomProvince = nomProvince;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    @Override
    public String toString() {
        return "AdresseInfra : " + "id=" + id + ", numeroRue=" + numeroRue + ", nomRue='" + nomRue + '\'' +
                ", nomQuartier='" + nomQuartier + '\'' + ", nomVille='" + nomVille + '\'' + ", nomProvince='" +
                nomProvince + '\'' + ", nomPays='" + nomPays + '\'';
    }
}
