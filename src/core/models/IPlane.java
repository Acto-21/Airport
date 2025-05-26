/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.List;

/**
 *
 * @author joelp
 */
public interface IPlane {
    void addFlight(IFlight flight);
    IPlane clone();
    
    String getId();
    String getBrand();
    String getModel();
    int getMaxCapacity();
    String getAirline();
    List<IFlight> getFlights();
    void setFlights(List<IFlight> flights);
    int getNumFlights();
}
