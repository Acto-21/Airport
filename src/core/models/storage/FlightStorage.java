/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import core.patterns.observer.Notifier;
import core.models.IFlight;
import core.patterns.observer.Observable;
import core.patterns.observer.Observer;
import core.services.duplicateChecker.DuplicateChecker;
import core.services.duplicateChecker.FlightDuplicateChecker;
import java.util.ArrayList;

/**
 *
 * @author User
 */

public class FlightStorage implements ObservableStorage<IFlight> , UpdatableStorage<IFlight>{

    private static FlightStorage instance;
    private final DuplicateChecker<IFlight> duplicateChecker;
    private final Observable notifier;
    private ArrayList<IFlight> flights;

    private FlightStorage(Observable notifier, DuplicateChecker<IFlight> duplicateChecker) {
        this.flights = new ArrayList<>();
        this.notifier = notifier;
        this.duplicateChecker = duplicateChecker;
    }

    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage(new Notifier(),new FlightDuplicateChecker());
        }
        return instance;
    }
    
    @Override
    public boolean addObserver(Observer observer) {
        return notifier.addObserver(observer);
    }
    
    @Override
    public boolean add(IFlight item) {
        for (IFlight f : this.flights) {
            if (duplicateChecker.isDuplicate(f, item)) {
                return false;
            }
        }
        this.flights.add(item);
        notifier.notifyAllObservers(1);
        return true;
    }
    
    @Override
    public boolean update(IFlight item) {

        for (int i = 0; i < this.flights.size(); i++) {
            if (duplicateChecker.isDuplicate(this.flights.get(i), item)) {
                this.flights.set(i, item);
                this.notifier.notifyAllObservers(2);
                return true;
            }
        }
        return false;
    }

    @Override
    public IFlight get(String id) {  
        for (IFlight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }

    public ArrayList<IFlight> getAll() {
        return this.flights;
    }
}
