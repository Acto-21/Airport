/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.patterns.prototype.Prototype;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Flight implements IFlight {

    private final String id;
    private List<IPassenger> passengers;
    private IPlane plane;
    private ILocation departureLocation;
    private ILocation scaleLocation;
    private ILocation arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;

    public Flight(String id, IPlane plane, ILocation departureLocation, ILocation arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;

        this.plane.addFlight(this);
    }

    public Flight(String id, IPlane plane, ILocation departureLocation, ILocation scaleLocation, ILocation arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;

        this.plane.addFlight(this);
    }

    public void addPassenger(IPassenger passenger) {
        this.passengers.add(passenger);
    }

    public void setPassengers(List<IPassenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ILocation getDepartureLocation() {
        return departureLocation;
    }

    @Override
    public ILocation getScaleLocation() {
        return scaleLocation;
    }

    @Override
    public ILocation getArrivalLocation() {
        return arrivalLocation;
    }

    @Override
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    @Override
    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    @Override
    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    @Override
    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    @Override
    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }

    @Override
    public IPlane getPlane() {
        return plane;
    }

    @Override
    public List<IPassenger> getPassengers() {
        return passengers;
    }

    @Override
    public int getNumPassengers() {
        return passengers.size();
    }

    @Override
    public LocalDateTime calculateArrivalDate() {
        return departureDate
                .plusHours(hoursDurationScale)
                .plusMinutes(minutesDurationScale)
                .plusHours(hoursDurationArrival)
                .plusMinutes(minutesDurationArrival);
    }

    @Override
    public IFlight clone() {
        IFlight copy;
        if (this.scaleLocation != null) {
            copy = new Flight(
                    this.id,
                    plane.clone(),
                    departureLocation.clone(),
                    scaleLocation.clone(),
                    arrivalLocation.clone(),
                    departureDate,
                    hoursDurationArrival,
                    minutesDurationArrival,
                    hoursDurationScale,
                    minutesDurationScale
            );
        } else {
            copy = new Flight(
                    this.id,
                    plane.clone(),
                    departureLocation.clone(),
                    arrivalLocation.clone(),
                    departureDate,
                    hoursDurationArrival,
                    minutesDurationArrival
            );
        }

        List<IPassenger> clonedPassengers = new ArrayList<>();
        for (IPassenger p : this.passengers) {
            clonedPassengers.add(p); 
        }
        copy.setPassengers(clonedPassengers);

        return copy;
    }

    public void setDepartureDate(LocalDateTime plusMinutes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
