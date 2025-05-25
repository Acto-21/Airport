/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Plane;

/**
 *
 * @author User
 */
public class PlaneFormatter implements Formatter<Plane> {

    @Override
    public String[] format(Plane object) {
        return new String[] {
            object.getId(),
            object.getBrand(),
            object.getModel(),
            String.valueOf(object.getMaxCapacity()),
            object.getAirline(),
            String.valueOf(object.getNumFlights())
        };
        /*
        for (Plane plane : (ArrayList<Plane>) response.getObject()) {
            model.addRow(new Object[]{plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), plane.getNumFlights()});
        }
        */
    }

}
