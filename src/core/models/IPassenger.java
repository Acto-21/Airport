/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.patterns.prototype.Prototype;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelp
 */
public interface IPassenger extends Prototype<IPassenger>{

    long getId();

    String getFirstname();

    String getLastname();

    List<IFlight> getFlights();
    
    @Override
    IPassenger clone();

    public void setFlights(ArrayList<IFlight> flights);

}
