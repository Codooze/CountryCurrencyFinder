package com.example.CountryCurrency.service;

import com.example.CountryCurrency.model.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CountryService {
    private final String API_URL = "https://restcountries.com/v3.1/name/";
    private final WebClient webClient;
    public CountryService() {
        this.webClient = WebClient.create(API_URL);
    }
    public Mono<Country> getCountryByName(String name) {
        // Use Java's HttpURLConnection or libraries like RestTemplate or WebClient to fetch data from the API
        // Parse the response to your model and return
        return webClient.get()
                .uri(name)
                .retrieve()
                .bodyToMono(Country[].class)
                .flatMap(countries -> {
                    if(countries.length > 0) {
                        return Mono.just(countries[0]);
                    } else {
                        return Mono.empty();
                    }
                });
    }
}
