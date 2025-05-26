/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Location;
import core.patterns.observer.Notifier;
import core.patterns.observer.Observable;
import core.patterns.observer.Observer;
import core.services.duplicateChecker.DuplicateChecker;
import core.services.duplicateChecker.LocationDuplicateChecker;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LocationStorage implements ObservableStorage<Location> {

    private static LocationStorage instance;

    private ArrayList<Location> locations;
    private final Observable notifier;
    private final DuplicateChecker<Location> duplicateChecker;

    private LocationStorage(Observable notifier, DuplicateChecker<Location> duplicateChecker) {
        this.locations = new ArrayList<>();
        this.notifier = notifier;
        this.duplicateChecker = duplicateChecker;
    }

    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage(new Notifier(),new LocationDuplicateChecker());
        }
        return instance;
    }

    @Override
    public boolean addObserver(Observer observer) {
        return notifier.addObserver(observer);
    }
    
    @Override
    public boolean add(Location item) {
        for (Location l : this.locations) {
            if (duplicateChecker.isDuplicate(l, item)) {
                return false;
            }
        }
        this.locations.add(item);
        this.notifier.notifyAllObservers(1);
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
