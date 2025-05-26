/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Flight;
import core.models.IFlight;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class ArrivalCalculator{

    public static LocalDateTime calculate(IFlight flight) {
        LocalDateTime departureDate = flight.getDepartureDate();
        int hoursDurationScale = flight.getHoursDurationScale();
        int hoursDurationArrival = flight.getHoursDurationArrival();
        int minutesDurationScale = flight.getMinutesDurationScale();
        int minutesDurationArrival = flight.getMinutesDurationArrival();
        return departureDate.plusHours(hoursDurationScale).plusHours(hoursDurationArrival).plusMinutes(minutesDurationScale).plusMinutes(minutesDurationArrival);
    }
    
}
