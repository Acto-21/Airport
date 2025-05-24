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
    public static Response addLocation(String id, String name, String city, String country, String longitudeStr, String latitudeStr) {
    
    if (id == null || id.length() != 3) {
        return new Response("The ID must be exactly 3 characters.", Status.BAD_REQUEST);
    }
    for (int i = 0; i < id.length(); i++) {
        char c = id.charAt(i);
        if (c < 'A' || c > 'Z') {
            return new Response("The ID can only contain uppercase letters.", Status.BAD_REQUEST);
        }
    }

    
    if (name == null || name.trim().isEmpty()) {
        return new Response("The name cannot be empty.", Status.BAD_REQUEST);
    }
    if (city == null || city.trim().isEmpty()) {
        return new Response("The city cannot be empty.", Status.BAD_REQUEST);
    }
    if (country == null || country.trim().isEmpty()) {
        return new Response("The country cannot be empty.", Status.BAD_REQUEST);
    }

    
    double latitude, longitude;
    try {
        latitude = Double.parseDouble(latitudeStr);
    } catch (NumberFormatException e) {
        return new Response("Latitude must be a valid number.", Status.BAD_REQUEST);
    }

    try {
        longitude = Double.parseDouble(longitudeStr);
    } catch (NumberFormatException e) {
        return new Response("Length must be a valid number.", Status.BAD_REQUEST);
    }

    if (latitude < -90 || latitude > 90) {
        return new Response("The latitude must be between -90 y 90.", Status.BAD_REQUEST);
    }

    if (longitude < -180 || longitude > 180) {
        return new Response("The length must be between -180 y 180.", Status.BAD_REQUEST);
    }

    
    LocationStorage storage = LocationStorage.getInstance();
    for (Location loc : storage.getAll()) {
        if (loc.getAirportId().equals(id)) {
            return new Response("A location with that ID already exists.", Status.BAD_REQUEST);
        }
    }

    
    Location nueva = new Location(id, name, city, country, longitude, latitude);
    storage.add(nueva);

    return new Response("Location added successfully.", Status.CREATED);
}


}
