package com.example.CountryCurrency;

import com.example.CountryCurrency.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CountryCurrencyApplication {

	private static final Logger logger = LoggerFactory.getLogger(CountryCurrencyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CountryCurrencyApplication.class, args);
		fetchAndDisplayCountryData();
	}

	public static void fetchAndDisplayCountryData() {
		final String API_URL = "https://restcountries.com/v3.1/name/colombia";

		RestTemplate restTemplate = new RestTemplate();

		// Fetching the API response as a raw string
		String response = restTemplate.getForObject(API_URL, String.class);

		if (response != null && !response.isEmpty()) {
			logger.info("API Response: {}", response);
		} else {
			logger.info("No country data found.");
		}
	}
}
