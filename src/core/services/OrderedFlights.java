/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;

import core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author joelp
 */
public class OrderedFlights {
    
           public static ArrayList<Flight> OrderFlights(ArrayList<Flight> originalList) {
            ArrayList<Flight> orderedList = new ArrayList<>();

            for (Flight vuelo : originalList) {
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
