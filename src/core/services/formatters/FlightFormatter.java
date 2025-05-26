/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Flight;
import core.models.IFlight;
import core.services.ArrivalCalculator;
import java.time.LocalDateTime;

public class FlightFormatter implements Formatter<IFlight> {

    @Override
    public String[] format(IFlight object) {
        LocalDateTime arrivalDate = ArrivalCalculator.calculate((Flight) object);
        return new String[] {
            object.getId(),
            object.getDepartureLocation().getAirportId(),
            object.getArrivalLocation().getAirportId(),
            object.getScaleLocation() == null ? "-" : object.getScaleLocation().getAirportId(),
            object.getDepartureDate().toString(),
            arrivalDate.toString(),
            object.getPlane().getId(),
            String.valueOf(object.getNumPassengers())
        };
    }   
}

