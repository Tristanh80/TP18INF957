package com.reservation.uqac.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTOResponse {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private Long hebergementId;
    private String typeChambre;
    private Long clientId;
    private float price;
}
