/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.patterns.prototype.Prototype;

/**
 *
 * @author edangulo
 */
public class Location implements Prototype<ILocation>, ILocation {
    
    private final String airportId;
    private String airportName;
    private String airportCity;
    private String airportCountry;
    private double airportLatitude;
    private double airportLongitude;

    public Location(String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
    }

    @Override
    public String getAirportId() {
        return airportId;
    }

    @Override
    public String getAirportName() {
        return airportName;
    }

    @Override
    public String getAirportCity() {
        return airportCity;
    }

    @Override
    public String getAirportCountry() {
        return airportCountry;
    }

    @Override
    public double getAirportLatitude() {
        return airportLatitude;
    }

    @Override
    public double getAirportLongitude() {
        return airportLongitude;
    }
    

    @Override
    public Location clone(){
        Location copy = new Location(this.airportId,this.airportName,this.airportCity,this.airportCountry,this.airportLatitude,this.airportLongitude);
        return copy;
    }
    
}
