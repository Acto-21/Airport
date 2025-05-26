/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.patterns.observer.Observer;

/**
 *
 * @author User
 */
public interface ObservableStorage<T> extends Storage<T>{
    boolean addObserver(Observer observer);
}
