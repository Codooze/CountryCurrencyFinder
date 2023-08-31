package com.example.CountryCurrency.controller;

import com.example.CountryCurrency.model.Country;
import com.example.CountryCurrency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

@GetMapping("/{name}")
public Mono<ResponseEntity<Country>> getCountryByName(@PathVariable String name) {
    return countryService.getCountryByName(name)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

/* when the getCountryByName method is called, Spring knows that the returned Country object should be
automatically converted to JSON (or XML, depending on configuration and request headers) and sent back to
the client as the response body.

- @RestController is a shorthand that makes creating RESTful controllers more concise and clear.
- It automatically handles the serialization of return values to the response body, eliminating the need
for @ResponseBody on each method.

.map(ResponseEntity::ok): The map operator is used to transform the data inside the Mono.
In this case, it's converting a Country object into a ResponseEntity<Country> with an HTTP status of 200 OK.
 The ResponseEntity::ok is a method reference that creates a new ResponseEntity with a 200 OK status.
*/