/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PassengerStorage implements Storage<Passenger>{

    private static PassengerStorage instance;
    
    private ArrayList<Passenger> passengers;
    
    private PassengerStorage() {
        this.passengers = new ArrayList<>();
    }
    
    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }
    
    @Override
    public boolean add(Passenger item) {
        for (Passenger p : this.passengers) {
            if (p.getId() == item.getId()) {
                return false;
            }
        }
        this.passengers.add(item);
        return true;
    }

    @Override
    public Passenger get(String id) {
        Long idLong = Long.parseLong(id);
        for (Passenger passenger: this.passengers) {
            if (passenger.getId() == idLong) {
                return passenger;
            }
        }
        return null;
    }
    
    public ArrayList<Passenger> getAll() {
        return this.passengers;
    }   
    
}
