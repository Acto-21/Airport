/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Flight;
import core.models.storage.FlightStorage;

/**
 *
 * @author User
 */
public class FlightCoordinator {

    public void delay(Flight flight, int hours, int minutes) {
        
        FlightStorage flightStorage = FlightStorage.getInstance();
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
        flightStorage.update(flight);
        
    }
    
}
