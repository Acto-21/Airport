/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.orderer;

import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author joelp
 */
public class PlaneOrderer {
    
    public static ArrayList<Plane> orderPlanes(ArrayList<Plane> originalList) {
    ArrayList<Plane> copiaList = new ArrayList<>();

    for (Plane plane : originalList) {
        Plane copia = plane.clone();
        boolean inserted = false;

        for (int i = 0; i < copiaList.size(); i++) {
            String idActual = copia.getId();
            String idComparar = copiaList.get(i).getId();

            int cmp = idActual.compareTo(idComparar);
            if (cmp < 0) {
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
