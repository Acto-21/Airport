/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Passenger;
import core.services.AgeCalculator;

/**
 *
 * @author User
 */
public class PassengerFormatter implements Formatter<Passenger>{

    @Override
    public String[] format(Passenger object) {
        int passengerAge = AgeCalculator.calculateAge(object.getBirthDate());
        PhoneFormatter phoneFormatter = new PhoneFormatter();
        String[] phone = phoneFormatter.format(object); 
        return new String[]{
            String.valueOf(object.getId()),
            object.getFullname(),
            object.getBirthDate().toString(),
            String.valueOf(passengerAge),
            phone[0],
            object.getCountry(),
            String.valueOf(object.getNumFlights())
        };
    }
    /*
    for (Passenger passenger : (ArrayList<Passenger>) response.getObject()) {
            model.addRow(new Object[]{passenger.getId(), passenger.getFullname(), passenger.getBirthDate(), passenger.calculateAge(), passenger.generateFullPhone(), passenger.getCountry(), passenger.getNumFlights()});
        }
    */
    
}
