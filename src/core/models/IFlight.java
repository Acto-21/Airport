/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author joelp
 */
public interface IFlight {
    String getId();
    ILocation getDepartureLocation();
    ILocation getScaleLocation();
    ILocation getArrivalLocation();
    LocalDateTime getDepartureDate();
    int getHoursDurationArrival();
    int getMinutesDurationArrival();
    int getHoursDurationScale();
    int getMinutesDurationScale();
    IPlane getPlane();
    List<IPassenger> getPassengers();
    int getNumPassengers();
    LocalDateTime calculateArrivalDate();
}
