package com.reservation.uqac.application.test;

import com.reservation.uqac.infrastructure.test.InitDatas;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DatasTestApplication {

    private final InitDatas initDatas;

    @Autowired
    public DatasTestApplication(InitDatas initDatas) {
        this.initDatas = initDatas;
    }

    @Operation(summary = "Init test datas")
    @PostMapping("/init/{nbHebergement}/{nbClient}")
    public void initDatas(@PathVariable("nbHebergement") int nbHebergement, @PathVariable("nbClient") int nbClient) {
        initDatas.initHebergement(nbHebergement);
        initDatas.initClients(nbClient);
    }


}
