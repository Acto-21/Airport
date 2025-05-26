/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.orderer;

import core.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author joelp
 */
public class PassengerOrder {

    public static ArrayList<Passenger> order(ArrayList<Passenger> originalList) {
        ArrayList<Passenger> copiaList = new ArrayList<>();

        for (Passenger pasajero : originalList) {
            Passenger copia = pasajero.clone();
            boolean inserted = false;

            for (int i = 0; i < copiaList.size(); i++) {
                if (copia.getId() < copiaList.get(i).getId()) {
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
