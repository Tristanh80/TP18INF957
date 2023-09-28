package com.reservation.uqac.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
}
