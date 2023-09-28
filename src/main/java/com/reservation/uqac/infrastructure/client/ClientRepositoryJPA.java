package com.reservation.uqac.infrastructure.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositoryJPA extends JpaRepository<ClientInfra, Long> {

    ClientInfra findByNomAndPrenom(String nom, String prenom);

    ClientInfra findByEmail(String email);

    ClientInfra findByTelephone(String telephone);
}
