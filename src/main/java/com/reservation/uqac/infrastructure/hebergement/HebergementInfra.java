package com.reservation.uqac.infrastructure.hebergement;

import com.reservation.uqac.domaine.hebergement.Service;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import com.reservation.uqac.domaine.hebergement.TypeHebergement;
import com.reservation.uqac.infrastructure.region.AdresseInfra;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class HebergementInfra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nomHebergement;

    @Enumerated(EnumType.STRING)
    private TypeHebergement type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private AdresseInfra adresseInfra;

    @ElementCollection
    @CollectionTable(name = "types_chambres", joinColumns = @JoinColumn(name = "hebergement_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type_chambre")
    private Set<TypeChambre> typesChambres;

    private Integer nombreChambresSimple;

    private Float prixChambreSimple;

    private Integer nombreChambresDouble;

    private Float prixChambreDouble;

    private Integer nombreChambresSuite;

    private Float prixChambreSuite;

    @ElementCollection
    @CollectionTable(name = "services_hebergement", joinColumns = @JoinColumn(name = "hebergement_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "service")
    private Set<Service> services;

    public HebergementInfra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomHebergement() {
        return nomHebergement;
    }

    public void setNomHebergement(String nomHebergement) {
        this.nomHebergement = nomHebergement;
    }

    public TypeHebergement getType() {
        return type;
    }

    public void setType(TypeHebergement type) {
        this.type = type;
    }

    public AdresseInfra getAdresse() {
        return adresseInfra;
    }

    public void setAdresse(AdresseInfra adresseInfra) {
        this.adresseInfra = adresseInfra;
    }

    public Set<TypeChambre> getTypesChambres() {
        return typesChambres;
    }

    public void setTypesChambres(Set<TypeChambre> typesChambres) {
        this.typesChambres = typesChambres;
    }

    public Integer getNombreChambresSimple() {
        return nombreChambresSimple;
    }

    public void setNombreChambresSimple(Integer nombreChambresSimple) {
        this.nombreChambresSimple = nombreChambresSimple;
    }

    public Integer getNombreChambresDouble() {
        return nombreChambresDouble;
    }

    public void setNombreChambresDouble(Integer nombreChambresDouble) {
        this.nombreChambresDouble = nombreChambresDouble;
    }

    public Integer getNombreChambresSuite() {
        return nombreChambresSuite;
    }

    public void setNombreChambresSuite(Integer nombreChambresSuite) {
        this.nombreChambresSuite = nombreChambresSuite;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public AdresseInfra getAdresseInfra() {
        return adresseInfra;
    }

    public void setAdresseInfra(AdresseInfra adresseInfra) {
        this.adresseInfra = adresseInfra;
    }

    public Float getPrixChambreSimple() {
        return prixChambreSimple;
    }

    public void setPrixChambreSimple(Float prixChambreSimple) {
        this.prixChambreSimple = prixChambreSimple;
    }

    public Float getPrixChambreDouble() {
        return prixChambreDouble;
    }

    public void setPrixChambreDouble(Float prixChambreDouble) {
        this.prixChambreDouble = prixChambreDouble;
    }

    public Float getPrixChambreSuite() {
        return prixChambreSuite;
    }

    public void setPrixChambreSuite(Float prixChambreSuite) {
        this.prixChambreSuite = prixChambreSuite;
    }

    @Override
    public String toString() {
        return "HebergementInfra :" + "id=" + id + ", nomHebergement='" + nomHebergement + '\'' + ", type=" + type +
                ", adresse=" + adresseInfra + ", typesChambres=" + typesChambres + ", nombreChambresSimple=" +
                nombreChambresSimple + ", prixChambreSimple=" + prixChambreSimple + ", nombreChambresDouble=" +
                nombreChambresDouble + ", prixChambreDouble=" + prixChambreDouble + ", nombreChambresSuite=" +
                nombreChambresSuite + ", prixChambreSuite=" + prixChambreSuite + ", services=" + services + "\n";
    }
}
