package com.example.CountryCurrency.model;

import java.util.Currency;

import java.util.Map;

public class Country {
    private Name name;
    private Map<String, CurrencyDetail> currencies;

    // getters, setters, constructors

    public static class Name {
        private String common;

        // getters, setters, constructors
    }

    private static class CurrencyDetail {
        private String name;
        private String symbol;

        // getters, setters, constructors
    }
}
