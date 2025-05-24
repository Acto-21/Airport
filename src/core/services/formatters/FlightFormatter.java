/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Flight;
import core.services.ArrivalCalculator;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class FlightFormatter implements Formatter<Flight>{

    @Override
    public Object[] format(Flight object) {
        LocalDateTime arrivalDate = ArrivalCalculator.calculate(object);
        return new Object[] {
            object.getId(),
            object.getDepartureLocation().getAirportId(),
            object.getArrivalLocation().getAirportId(),
            object.getScaleLocation() == null ? "-" : object.getScaleLocation().getAirportId(),
            object.getDepartureDate(),
            arrivalDate,
            object.getPlane().getId(),
            object.getNumPassengers()
        };
    }

   
    
}
