/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;


import core.models.IFlight;
import core.models.IPassenger;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;

/**
 *
 * @author User
 */
public class PassengerManager {

    public void addPassenger(IFlight flight, IPassenger passenger) {
        FlightStorage flightStorage = FlightStorage.getInstance();
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        flight.getPassengers().add(passenger);
        passenger.getFlights().add(flight);
        passengerStorage.update(passenger);
        flightStorage.update(flight);
        
    }
    
}
