/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;
import core.models.storage.loaders.LocationLoader;
import core.models.storage.reader.JsonFileReader;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LocationController {

    public static Response loadLocationsFromJson(String path) {
        try {
            LocationStorage locations = LocationStorage.getInstance();
            LocationLoader loader = new LocationLoader(locations);
            String jsonLocations = JsonFileReader.readFile(path);
            loader.loadFromFile(jsonLocations);
            return new Response("Locations loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error loading locations: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllLocations() {
        ArrayList<Location> originalList = LocationStorage.getInstance().getAll();
        ArrayList<Location> copiaList = new ArrayList<>();
        for(Location locacion: originalList){
            try {
            copiaList.add((Location) locacion.clone());
            } catch (Exception e) {
                return new Response("Error cloning locations: ", Status.INTERNAL_SERVER_ERROR,new ArrayList<>());
            }
        }
        return new Response("Locations retrieved successfully.", Status.OK, copiaList);
    }
}
