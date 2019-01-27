package com.jesseliii.pragueia;

public class Driver extends Human {
    private String[] languages = new String[5];

    public Driver(String[] languages, String name, String description, String contact, double rating, int ratingNum) {
        super(name, description, contact, rating, ratingNum);
        this.languages = languages;
    }

    public Driver(String name){
        super(name);
    }
}

