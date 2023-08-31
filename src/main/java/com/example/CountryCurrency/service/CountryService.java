package com.example.CountryCurrency.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);
    private final String API_URL = "https://restcountries.com/v3.1/name/";
    private final WebClient webClient;

    public CountryService() {
        this.webClient = WebClient.create(API_URL);
    }

    public Mono<JsonNode> getCountryByName(String name) {
        logger.debug("Fetching information for country: {}", name);

        return webClient.get()
                .uri(name)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
