package com.jesseliii.pragueia;

public abstract class Human {
    private String name;
    private String description;
    private String contact;

    private double rating;
    private int ratingNum;

    public Human(String name, String description, String contact, double rating, int ratingNum) {
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.rating = rating;
        this.ratingNum = ratingNum;
    }

    public Human(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
