package com.reservation.uqac.application.hebergement;

import com.reservation.uqac.business.HebergementInterface;
import com.reservation.uqac.domaine.hebergement.HebergementDomaine;
import com.reservation.uqac.dto.client.ExigenceClientDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hebergement")
public class HebergementApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(HebergementApplication.class);

    private final HebergementInterface hebergementInterface;

    @Autowired
    public HebergementApplication(HebergementInterface hebergementInterface) {
        this.hebergementInterface = hebergementInterface;
    }

    @Operation(summary = "Get all hebergement")
    @GetMapping("/get")
    public ResponseEntity<List<HebergementDomaine>> getHebergement() {
        List<HebergementDomaine> hebergementDomaineList = hebergementInterface.getAllHebergement();
        if (hebergementDomaineList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hebergementDomaineList);
    }

    @Operation(summary = "Get hebergement by criteria")
    @PostMapping("/getByCriteria")
    public ResponseEntity<List<HebergementDomaine>> getHebergementByCriteria(@RequestBody ExigenceClientDTO exigenceClientDTO) {
        LOGGER.info("Get hebergement by criteria");
        LOGGER.info("Exigence client: {}", exigenceClientDTO);
        List<HebergementDomaine> hebergementDomaineList = hebergementInterface.getHebergementByCriteria(exigenceClientDTO);
        if (hebergementDomaineList == null) {
            return ResponseEntity.notFound().build();
        }
        LOGGER.info("Hebergement list: {}", hebergementDomaineList);
        return ResponseEntity.ok(hebergementDomaineList);
    }

}
