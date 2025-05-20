/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import core.models.storage.loaders.FlightLoader;
import core.models.storage.reader.JsonFileReader;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class FlightController {

    public static Response loadFlightsFromJson(String path) {
        try {
            FlightStorage flights = FlightStorage.getInstance();
            PlaneStorage planes = PlaneStorage.getInstance();
            LocationStorage locations = LocationStorage.getInstance();
            FlightLoader loader = new FlightLoader(flights, planes, locations);
            String jsonFlights = JsonFileReader.readFile(path);
            loader.loadFromFile(jsonFlights);
            return new Response("Flights loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error loading flights: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllFlights() {
        ArrayList<Flight> originalList = FlightStorage.getInstance().getAll();
        ArrayList<Flight> copiaList = new ArrayList<>();

        for (Flight vuelo : originalList) {
            try {
                copiaList.add((Flight) vuelo.clone());
            } catch (Exception e) {
                return new Response("Error cloning flights: ", Status.INTERNAL_SERVER_ERROR,new ArrayList<>());
            }
        }

        return new Response("Flights retrieved successfully.", Status.OK, copiaList);
    }
}
