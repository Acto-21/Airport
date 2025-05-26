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
public interface IPassenger {

    String getId();

    String getName();

    String getLastName();

    List<IFlight> getFlights();

    void addFlight(IFlight flight);
}
