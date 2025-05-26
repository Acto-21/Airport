/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.orderer;

import core.models.ILocation;
import java.util.ArrayList;

/**
 *
 * @author joelp
 */
public class LocationOrderer {

    public static ArrayList<ILocation> order(ArrayList<ILocation> originalList) {
        ArrayList<ILocation> copiaList = new ArrayList<>();

        for (ILocation location : originalList) {
            ILocation copia = location.clone();
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
