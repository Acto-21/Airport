/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import core.patterns.observer.Observable;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class FlightStorage extends Observable implements Storage<Flight> {

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
        notifyAll(1);
        return true;
    }
    
    public boolean update(Flight item) {
        for (int i = 0; i < this.flights.size(); i++) {
            if (this.flights.get(i).getId().equals(item.getId())) {
                this.flights.set(i, item);
                notifyAll(2);
                return true;
            }
        }
        return false;
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
