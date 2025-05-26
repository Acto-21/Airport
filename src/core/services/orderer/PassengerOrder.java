/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.orderer;


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
            long passengerId = passenger.getId();

            for (int i = 0; i < orderedList.size(); i++) {
                long currentId = orderedList.get(i).getId();
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

