/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public abstract class Observable {
    protected ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }
    
    public boolean addObserver(Observer observer) {
        this.observers.add(observer);
        observer.setObservable(this);
        return true;
    }
    
    protected void notifyAll(int value) {
        for (Observer observer : this.observers) {
            observer.notify(value);
        }
    }
}
