package com.reservation.uqac.domaine.mapper;

import com.reservation.uqac.domaine.hebergement.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MapperServiceFromString {

    private MapperServiceFromString() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<Service> mapFromString(String services) {
        Set<String> servicesList = Stream.of(services.split(","))
                .collect(java.util.stream.Collectors.toSet());
        Set<Service> servicesSet = new HashSet<>();
        for (String service : servicesList) {
            try {
                servicesSet.add(Service.valueOf(service.trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Service " + service + " n'existe pas");
            }
        }
        return servicesSet;
    }
}
