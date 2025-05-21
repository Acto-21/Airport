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
    public static Response addLocation(String id, String name, String city, String country, double longitude, double latitude) {
        
        if (id == null || id.length() != 3) {
            return new Response("El ID debe tener exactamente 3 caracteres.", Status.BAD_REQUEST);
        }
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (c < 'A' || c > 'Z') {
                return new Response("El ID solo puede contener letras mayúsculas.", Status.BAD_REQUEST);
            }
        }
        
        if (name == null || name.trim().isEmpty()) {
            return new Response("El nombre no puede estar vacío.", Status.BAD_REQUEST);
        }
        if (city == null || city.trim().isEmpty()) {
            return new Response("La ciudad no puede estar vacía.", Status.BAD_REQUEST);
        }
        if (country == null || country.trim().isEmpty()) {
            return new Response("El país no puede estar vacío.", Status.BAD_REQUEST);
        }
        
        if (latitude < -90 || latitude > 90) {
            return new Response("La latitud debe estar entre -90 y 90.", Status.BAD_REQUEST);
        }
        
        if (longitude < -180 || longitude > 180) {
            return new Response("La longitud debe estar entre -180 y 180.", Status.BAD_REQUEST);
        }
        
        LocationStorage storage = LocationStorage.getInstance();
        for (Location loc : storage.getAll()) {
            if (loc.getAirportId().equals(id)) {
                return new Response("Ya existe una ubicación con ese ID.", Status.BAD_REQUEST);
            }
        }
        
        Location nueva = new Location(id, name, city, country, longitude, latitude);
        storage.add(nueva);

        return new Response("Ubicación agregada correctamente.", Status.CREATED);
    }

}
