/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Location;

/**
 *
 * @author User
 */
public class LocationFormatter implements Formatter<Location>{

    @Override
    public String[] format(Location object) {
        return new String[]{
          object.getAirportId(),
          object.getAirportName(),
          object.getAirportCity(),
          object.getAirportCountry()
        };
    }
    /*
    for (Location location : (ArrayList<Location>) response.getObject()) {
            model.addRow(new Object[]{location.getAirportId(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()});
        }
    */
}
