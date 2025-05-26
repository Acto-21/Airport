/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Passenger;
import core.patterns.observer.Notifier;
import core.patterns.observer.Observable;
import core.patterns.observer.Observer;
import core.services.duplicateChecker.DuplicateChecker;
import core.services.duplicateChecker.PassengerDuplicateChecker;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PassengerStorage implements UpdatableStorage<Passenger>, ObservableStorage<Passenger> {

    private static PassengerStorage instance;

    private ArrayList<Passenger> passengers;
    private final Observable notifier;
    private final DuplicateChecker<Passenger> duplicateChecker;

    private PassengerStorage(Observable notifier, DuplicateChecker<Passenger> duplicateChecker) {
        this.passengers = new ArrayList<>();
        this.notifier = notifier;
        this.duplicateChecker = duplicateChecker;
    }

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage(new Notifier(),new PassengerDuplicateChecker());
        }
        return instance;
    }

    @Override
    public boolean addObserver(Observer observer) {
        return notifier.addObserver(observer);
    }

    @Override
    public boolean add(Passenger item) {
        for (Passenger p : this.passengers) {
            if (duplicateChecker.isDuplicate(p, item)) {
                return false;
            }
        }
        this.passengers.add(item);
        notifier.notifyAllObservers(1);
        return true;
    }

    @Override
    public boolean update(Passenger item) {
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).getId() == item.getId()) {
                this.passengers.set(i, item);
                notifier.notifyAllObservers(2);
                return true;
            }
        }
        return false;
    }

    @Override
    public Passenger get(String id) {
        Long idLong = Long.parseLong(id);
        for (Passenger passenger : this.passengers) {
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
