/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Plane;
import core.patterns.observer.Notifier;
import core.patterns.observer.Observable;
import core.patterns.observer.Observer;
import core.services.duplicateChecker.DuplicateChecker;
import core.services.duplicateChecker.PlaneDuplicateChecker;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PlaneStorage implements ObservableStorage<Plane> {

    private static PlaneStorage instance;

    private ArrayList<Plane> planes;
    private final Observable notifier;
    private final DuplicateChecker<Plane> duplicateChecker;
    
    private PlaneStorage(Observable notifier, DuplicateChecker<Plane> duplicateChecker) {
        this.planes = new ArrayList<>();
        this.notifier = notifier;
        this.duplicateChecker = duplicateChecker;
    }

    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage(new Notifier(),new PlaneDuplicateChecker());
        }
        return instance;
    }
    
    @Override
    public boolean addObserver(Observer observer) {
        return notifier.addObserver(observer);
    }

    @Override
    public boolean add(Plane item) {
        for (Plane p : this.planes) {
            if (duplicateChecker.isDuplicate(p,item)) {
                return false;
            }
        }
        this.planes.add(item);
        notifier.notifyAllObservers(1);
        return true;
    }

    @Override
    public Plane get(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    public ArrayList<Plane> getAll() {
        return this.planes;
    }
}
