package com.camila.covidapp.model;

public class Country {
    public String name;
    public String code;
    public String totalCases;
    public String totalDeaths;
    public String totalRecovered;

    public Country(){

    }

    public Country(String name, String code, String totalCases, String totalDeaths, String totalRecovered) {
        this.name = name;
        this.code = code;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
    }
}
