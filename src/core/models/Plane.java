/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.patterns.prototype.Prototype;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Plane implements IPlane {

    private final String id;
    private String brand;
    private String model;
    private final int maxCapacity;
    private String airline;
    private List<IFlight> flights;

    public Plane(String id, String brand, String model, int maxCapacity, String airline) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
        this.flights = new ArrayList<>();
    }

    @Override
    public void addFlight(IFlight flight) {
        this.flights.add(flight);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public String getAirline() {
        return airline;
    }

    @Override
    public List<IFlight> getFlights() {
        return flights;
    }

    @Override
    public void setFlights(List<IFlight> flights) {
        this.flights = flights;
    }

    @Override
    public int getNumFlights() {
        return flights.size();
    }

    @Override
    public IPlane clone() {
        IPlane copy = new Plane(this.id, this.brand, this.model, this.maxCapacity, this.airline);
        copy.setFlights(this.flights);
        return copy;
    }
}
