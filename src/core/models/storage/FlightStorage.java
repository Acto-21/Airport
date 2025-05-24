/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class FlightStorage implements Storage<Flight> {

    private static FlightStorage instance;

    private ArrayList<Flight> flights;

    private FlightStorage() {
        this.flights = new ArrayList<>();
    }

    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Flight item) {
        for (Flight f : this.flights) {
            if (f.getId().equals(item.getId())) {
                return false;
            }
        }
        this.flights.add(item);
        return true;
    }

    @Override
    public Flight get(String id) {  
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }

    public ArrayList<Flight> getAll() {
        return this.flights;
    }
}
