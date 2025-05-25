/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Flight;
import core.models.Passenger;
import core.services.ArrivalCalculator;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PassengerFlightFormatter implements Formatter<Flight>{

    @Override
    public String[] format(Flight object) {
        LocalDateTime arrivalDate = ArrivalCalculator.calculate(object);
        return new String[]{
            object.getId(),
            object.getDepartureDate().toString(),
            arrivalDate.toString()
        };
        
    }
    
}
