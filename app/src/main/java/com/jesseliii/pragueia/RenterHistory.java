package com.jesseliii.pragueia;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RenterHistory extends AppCompatActivity {
    private int busID;
    private String date;
    private int daysRented;
    private Driver driver;
    private String complaints;

    public RenterHistory(int busID, String date, int daysRented, Driver driver, String complaints) {
        this.busID = busID;
        this.date = date;
        this.daysRented = daysRented;
        this.driver = driver;
        this.complaints = complaints;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

}
