/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

/**
 *
 * @author User
 */
public interface Observable {

    boolean addObserver(Observer observer);
    
    void notifyAllObservers(int value);
    
}
