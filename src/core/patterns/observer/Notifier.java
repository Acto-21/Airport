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
public class Notifier implements Observable {

    private final ArrayList<Observer> observers = new ArrayList<>();


    @Override
    public boolean addObserver(Observer observer) {
        this.observers.add(observer);
        observer.setObservable(this);
        return true;
    }

    @Override
    public void notifyAllObservers(int value) {
        for (Observer observer : this.observers) {
            observer.notify(value);
        }
    }
}
