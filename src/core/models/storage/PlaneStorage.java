/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PlaneStorage implements Storage<Plane> {

    private static PlaneStorage instance;

    private ArrayList<Plane> planes;

    private PlaneStorage() {
        this.planes = new ArrayList<>();
    }

    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }

    @Override
    public boolean add(Plane item) {
        for (Plane p : this.planes) {
            if (p.getId().equals(item.getId())) {
                return false;
            }
        }
        this.planes.add(item);
        return true;
    }

    @Override
    public Plane get(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    public ArrayList<Plane> getAll() {
        return this.planes;
    }
}
