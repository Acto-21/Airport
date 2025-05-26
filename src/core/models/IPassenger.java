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

    long getId();

    String getFirstname();

    String getLastname();

    List<IFlight> getFlights();

}
