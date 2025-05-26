/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services;


/**
 *
 * @author joelp
 */

import core.models.IPassenger;
import java.util.ArrayList;

public class PassengerOrder {

    public static ArrayList<IPassenger> order(ArrayList<IPassenger> originalList) {
        ArrayList<IPassenger> orderedList = new ArrayList<>();

        for (IPassenger passenger : originalList) {
            boolean inserted = false;
            long passengerId = Long.parseLong(passenger.getId());

            for (int i = 0; i < orderedList.size(); i++) {
                long currentId = Long.parseLong(orderedList.get(i).getId());
                if (passengerId < currentId) {
                    orderedList.add(i, passenger);
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                orderedList.add(passenger);
            }
        }

        return orderedList;
    }
}

