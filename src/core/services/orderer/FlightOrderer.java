/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.orderer;

import core.models.Flight;
import core.models.IFlight;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelp
 */
public class FlightOrderer {
    
           public static ArrayList<IFlight> order(ArrayList<IFlight> originalList) {
            ArrayList<IFlight> orderedList = new ArrayList<>();

            for (IFlight vuelo : originalList) {
                int insertIndex = 0;
                while (insertIndex < orderedList.size()
                        && vuelo.getDepartureDate().isAfter(orderedList.get(insertIndex).getDepartureDate())) {
                    insertIndex++;
                }
                orderedList.add(insertIndex, vuelo);
            }

            return orderedList;
        }
    

}
