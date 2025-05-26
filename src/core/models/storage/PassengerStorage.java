/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.patterns.observer.Notifier;
import core.models.IPassenger;
import core.patterns.observer.Observable;
import core.patterns.observer.Observer;
import core.services.duplicateChecker.DuplicateChecker;
import core.services.duplicateChecker.PassengerDuplicateChecker;
import java.util.ArrayList;


/**
 *
 * @author User
 */
public class PassengerStorage implements UpdatableStorage<IPassenger>, ObservableStorage<IPassenger> {

    private static PassengerStorage instance;
    private ArrayList<IPassenger> passengers;
    private final Observable notifier;
    private final DuplicateChecker<IPassenger> duplicateChecker;

    private PassengerStorage(Observable notifier, DuplicateChecker<IPassenger> duplicateChecker) {
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
    public boolean add(IPassenger item) {
        for (IPassenger p : this.passengers) {
            if (duplicateChecker.isDuplicate(p, item)) {
                return false;
            }
        }
        this.passengers.add(item);
        notifier.notifyAllObservers(1);
        return true;
    }

    @Override
    public boolean update(IPassenger item) {
        for (int i = 0; i < this.passengers.size(); i++) {
            if (duplicateChecker.isDuplicate(this.passengers.get(i), item)) {
                this.passengers.set(i, item);
                notifier.notifyAllObservers(2);
                return true;
            }
        }
        return false;
    }

    @Override
    public IPassenger get(String id) {
        Long idString = Long.parseLong(id);
        for (IPassenger passenger : this.passengers) {
            if (passenger.getId() == idString) {
                return passenger;
            }
        }
        return null;
    }

    public ArrayList<IPassenger> getAll() {
        return this.passengers;
    }
}


