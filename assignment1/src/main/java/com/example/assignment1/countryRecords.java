package com.example.assignment1;

public class countryRecords {


//declaring variables for the table

    private int affected ;
    private int recovered;
    private int deaths;
    private int country_Id;
    private String country_Name;
// creating constructor
    public countryRecords(int country_Id, String country_Name, int affected, int recovered, int deaths) {
        this.country_Id = country_Id;
        this.country_Name = country_Name;
        this.affected = affected;
        this.recovered = recovered;
        this.deaths = deaths;
    }


    //getter and setters

    public int getCountry_Id() {
        return country_Id;
    }

    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }



    public int getAffected() {
        return affected;
    }

    public void setAffected(int affected) {
        affected = affected;
    }


    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }


    public String getCountry_Name() {
        return country_Name;
    }

    public void setCountry_Name(String country_Name) {
        this.country_Name = country_Name;
    }
}
