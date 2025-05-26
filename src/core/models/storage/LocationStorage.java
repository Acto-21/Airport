/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Location;
import core.patterns.observer.Observable;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LocationStorage extends Observable implements Storage<Location> {

    private static LocationStorage instance;

    private ArrayList<Location> locations;

    private LocationStorage() {
        this.locations = new ArrayList<>();
    }

    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Location item) {
        for (Location l : this.locations) {
            if (l.getAirportId().equals(item.getAirportId())) {
                return false;
            }
        }
        this.locations.add(item);
        notifyAll(1);
        return true;
    }

    @Override
    public Location get(String id) {
        for (Location location : this.locations) {
            if (location.getAirportId().equals(id) ) {
                return location;
            }
        }
        return null;
    }

    public ArrayList<Location> getAll() {
        return this.locations;
    }
}
