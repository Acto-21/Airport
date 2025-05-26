/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.IPassenger;
import core.patterns.observer.Observable;
import java.util.ArrayList;

public class PassengerStorage extends Observable implements Storage<IPassenger> {

    private static PassengerStorage instance;

    private ArrayList<IPassenger> passengers;

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
    public boolean add(IPassenger item) {
        for (IPassenger p : this.passengers) {
            if (p.getId().equals(item.getId())) {
                return false;
            }
        }
        this.passengers.add(item);
        notifyAll(1);
        return true;
    }

    public boolean update(IPassenger item) {
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).getId().equals(item.getId())) {
                this.passengers.set(i, item);
                notifyAll(2);
                return true;
            }
        }
        return false;
    }

    @Override
    public IPassenger get(String id) {
        for (IPassenger passenger : this.passengers) {
            if (passenger.getId().equals(id)) {
                return passenger;
            }
        }
        return null;
    }

    public ArrayList<IPassenger> getAll() {
        return this.passengers;
    }
}


