/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import core.models.Passenger;

/**
 *
 * @author User
 */
public class UserManager{
    
    private static UserManager instance;
    private Passenger currentUser;
    public static final int USER_CHANGED = 3;
    private final Notifier notifier;

    private UserManager() {
        this.currentUser = null;
        this.notifier = new Notifier();
    }
    
    public boolean addObserver(Observer observer) {
        return notifier.addObserver(observer);
    }
    
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    
    public void setCurrentUser(Passenger user) {
        this.currentUser = user;
        notifier.notifyAllObservers(USER_CHANGED); //Notifica el cambio de usuario
    }

    public Passenger getCurrentUser() {
        return currentUser;
    }
    
}
