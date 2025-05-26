/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.patterns.prototype.Prototype;

/**
 *
 * @author joelp
 */

public interface ILocation extends Prototype<ILocation>{

    String getAirportId(); 
    String getAirportName();
    String getAirportCity();
    String getAirportCountry();
    double getAirportLatitude();
    double getAirportLongitude();

    @Override
    ILocation clone();
}

