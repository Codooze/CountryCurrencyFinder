package com.example.CountryCurrency.controller;

import com.example.CountryCurrency.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2/countries")
public class CountryControllerV2 {

    private final CountryService countryService;
    private static final Logger logger = LoggerFactory.getLogger(CountryControllerV2.class);

    @Autowired
    public CountryControllerV2(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{name}")
    public Mono<ResponseEntity<String>> getCountryByName(@PathVariable String name) {
        logger.debug("Entering CountryControllerV2.getCountryByName with name: {}", name);
        System.out.println("Entering CountryControllerV2.getCountryByName with name: " + name);

        return countryService.getCountryByName(name)
                .doOnNext(jsonResponse -> {
                    logger.debug("Country data retrieved: {}", jsonResponse);
                    System.out.println("Country data retrieved: " + jsonResponse);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
