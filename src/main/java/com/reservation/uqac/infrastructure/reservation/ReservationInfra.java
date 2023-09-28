package com.reservation.uqac.infrastructure.reservation;

import com.reservation.uqac.infrastructure.client.ClientInfra;
import com.reservation.uqac.infrastructure.hebergement.HebergementInfra;
import com.reservation.uqac.domaine.hebergement.TypeChambre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;

@Entity
public class ReservationInfra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin", nullable = false)
    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "hebergement_id", nullable = false)
    private HebergementInfra hebergementInfra;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_chambre", nullable = false)
    private TypeChambre typeChambre;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientInfra clientInfra;

    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public HebergementInfra getHebergementInfra() {
        return hebergementInfra;
    }

    public void setHebergementInfra(HebergementInfra hebergementInfra) {
        this.hebergementInfra = hebergementInfra;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    public ClientInfra getClientInfra() {
        return clientInfra;
    }

    public void setClientInfra(ClientInfra clientInfra) {
        this.clientInfra = clientInfra;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
