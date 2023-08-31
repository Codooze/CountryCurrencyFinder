package com.example.CountryCurrency.controller;

import com.example.CountryCurrency.service.CountryService;
import com.fasterxml.jackson.databind.JsonNode;
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
public class CountryController {

    private final CountryService countryService;
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{name}")
    public Mono<ResponseEntity<String>> getCountryByName(@PathVariable String name) {
        System.out.println("Entering CountryControllerV2.getCountryByName with name: " + name);

        return countryService.getCountryByName(name)
                .flatMap(jsonNode -> {
                    if(jsonNode != null) {
                    JsonNode countryNode = jsonNode.get(0);
                    String countryName = countryNode.path("name").path("common").asText();
                    String currencyCode = countryNode.path("currencies").fieldNames().next();
                        System.out.println("Country: " + countryName + ", Currency Code: " + currencyCode);
                        return Mono.just(ResponseEntity.ok("Country: " + countryName + ", Currency Code: " + currencyCode));
                    } else {
                        return Mono.empty();
                    }
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
