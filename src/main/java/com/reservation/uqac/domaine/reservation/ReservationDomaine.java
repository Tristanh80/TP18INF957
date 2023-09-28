package com.reservation.uqac.domaine.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDomaine {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private Long hebergementId;
    private String typeChambre;
    private Long clientId;
    private float price;

    @Override
    public String toString() {
        return "Reservation : " + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", hebergementId" +
                "=" + hebergementId + ", typeChambre=" + typeChambre + ", clientId=" + clientId + ", price=" + price + "\n";
    }
}
