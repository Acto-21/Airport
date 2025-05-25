/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Location;
import java.util.ArrayList;

/**
 *
 * @author joelp
 */
public class OrderedLocations {

    public static ArrayList<Location> orderLocations(ArrayList<Location> originalList) {
        ArrayList<Location> copiaList = new ArrayList<>();

        for (Location location : originalList) {
            Location copia = location.clone();
            boolean inserted = false;

            for (int i = 0; i < copiaList.size(); i++) {

                if (copia.getAirportId().compareTo(copiaList.get(i).getAirportId()) < 0) {
                    copiaList.add(i, copia);
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                copiaList.add(copia);
            }
        }

        return copiaList;
    }

}
