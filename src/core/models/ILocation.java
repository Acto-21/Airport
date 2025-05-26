/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

/**
 *
 * @author joelp
 */

public interface ILocation {

    String getAirportId(); 

    String getAirportName();

    String getAirportCity();

    String getAirportCountry();

    double getAirportLatitude();

    double getAirportLongitude();

    ILocation clone();
}

