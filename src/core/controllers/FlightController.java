/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import core.models.storage.loaders.FlightLoader;
import core.models.storage.reader.JsonFileReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                return new Response("Error cloning flights: ", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
            }
        }

        return new Response("Flights retrieved successfully.", Status.OK, copiaList);
    }

    public static Response addFlight(String id, String planeId, String departureId, String arrivalId,String year, String month, String day, String hoursArrivalStr, String minutesArrivalStr,
                                 String scaleId, String hoursScaleStr, String minutesScaleStr) {

    
    if (id.length() != 6) {
        return new Response("The flight ID must be exactly 6 characters (3 letters followed by 3 numbers).", Status.BAD_REQUEST);
    }

    for (int i = 0; i < 3; i++) {
        char c = id.charAt(i);
        if (c < 'A' || c > 'Z') {
            return new Response("The first 3 characters of the ID must be uppercase letters.", Status.BAD_REQUEST);
        }
    }

    for (int i = 3; i < 6; i++) {
        char c = id.charAt(i);
        if (c < '0' || c > '9') {
            return new Response("The last 3 characters of the ID must be numbers.", Status.BAD_REQUEST);
        }
    }

    if (FlightStorage.getInstance().get(id) != null) {
        return new Response("A flight already exists with that ID.", Status.BAD_REQUEST);
    }

    
    Plane plane = PlaneStorage.getInstance().get(planeId);
    if (plane == null) {
        return new Response("The plane does not exist.", Status.BAD_REQUEST);
    }

    
    Location departure = LocationStorage.getInstance().get(departureId);
    if (departure == null) {
        return new Response("The exit location does not exist.", Status.BAD_REQUEST);
    }

    Location arrival = LocationStorage.getInstance().get(arrivalId);
    if (arrival == null) {
        return new Response("The arrival location does not exist.", Status.BAD_REQUEST);
    }

    
    LocalDate departureLocalDate;
    int intYear, intMonth, intDay;
    try {
        intYear = Integer.parseInt(year);
        int currentYear = LocalDate.now().getYear();
        if (intYear < currentYear || intYear > currentYear + 5) {
            return new Response("Please enter a valid year between " + currentYear + " and " + (currentYear + 5), Status.BAD_REQUEST);
        }
    } catch (NumberFormatException e) {
        return new Response("Year must be a number", Status.BAD_REQUEST);
    }

    try {
        intMonth = Integer.parseInt(month);
        if (intMonth < 1 || intMonth > 12) {
            return new Response("Month must be between 1 and 12", Status.BAD_REQUEST);
        }
    } catch (NumberFormatException e) {
        return new Response("Month must be a number", Status.BAD_REQUEST);
    }

    if (day.equals("Day")) {
        return new Response("You must choose a day before proceeding.", Status.BAD_REQUEST);
    }

    try {
        intDay = Integer.parseInt(day);
        departureLocalDate = LocalDate.of(intYear, intMonth, intDay);
        if (departureLocalDate.isBefore(LocalDate.now())) {
            return new Response("Departure date cannot be from the past", Status.BAD_REQUEST);
        }
    } catch (NumberFormatException e) {
        return new Response("Day must be a number", Status.BAD_REQUEST);
    } catch (DateTimeException e) {
        return new Response("Departure date is invalid or does not exist.", Status.BAD_REQUEST);
    }

    
    LocalDateTime departureDate = departureLocalDate.atStartOfDay(); 

    
    int hoursArrival = Integer.parseInt(hoursArrivalStr);
    int minutesArrival = Integer.parseInt(minutesArrivalStr);
    int hoursScale = Integer.parseInt(hoursScaleStr);
    int minutesScale = Integer.parseInt(minutesScaleStr);

    
    if (hoursArrival == 0 && minutesArrival == 0) {
        return new Response("The duration of the flight must be greater than 00:00.", Status.BAD_REQUEST);
    }

    
    Location scale = null;
    boolean hayEscala = (scaleId != null && !scaleId.isEmpty());

    if (hayEscala) {
        scale = LocationStorage.getInstance().get(scaleId);
        if (scale == null) {
            return new Response("Scale location does not exist.", Status.BAD_REQUEST);
        }
        if (hoursScale == 0 && minutesScale == 0) {
            return new Response("Scale time must be greater than 0 if a scale is specified.", Status.BAD_REQUEST);
        }
    } else {
        if (hoursScale != 0 || minutesScale != 0) {
            return new Response("There can be no layover time if there is no layover location.", Status.BAD_REQUEST);
        }
    }

    
    Flight flight;
    if (hayEscala) {
        flight = new Flight(id, plane, departure, scale, arrival, departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
    } else {
        flight = new Flight(id, plane, departure, arrival, departureDate, hoursArrival, minutesArrival);
    }

    FlightStorage.getInstance().add(flight);
    return new Response("Flight added successfully.", Status.OK);
}


}
