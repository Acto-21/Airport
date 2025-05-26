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
import core.models.storage.loaders.FlightJSonLoader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import core.models.storage.reader.LineFileReader;
import core.models.storage.reader.Reader;
import core.services.FlightCoordinator;
import core.services.orderer.FlightOrderer;
import core.services.formatters.FlightFormatter;
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
            FlightJSonLoader loader = new FlightJSonLoader(flights, planes, locations);
            Reader reader = new LineFileReader();
            String jsonFlights = (String) reader.read(path);
            loader.loadFromFile(jsonFlights);
            return new Response("Flights loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error loading flights: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllFlights() {
    
    ArrayList<Flight> orderedList = new ArrayList<>();
    
        try {
            ArrayList<Flight> originalList = FlightStorage.getInstance().getAll();
            orderedList = FlightOrderer.order(originalList);
        } catch (Exception e) {
            return new Response("Error cloning flights.", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }
    
    return new Response("Flights retrieved successfully.", Status.OK, orderedList);
}

    public static Response addFlight(String id, String planeId, String departureId, String arrivalId, String year, String month, String day, String hour, String minutes, String hoursArrivalStr, String minutesArrivalStr,
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
        if(departure == arrival){
            return new Response("The departure and arrival location cannot be the same", Status.BAD_REQUEST);
        }
        LocalDateTime departureLocalDate;
        int intYear, intMonth, intDay;
        if (year.equals("")) {
            return new Response("You must choose a month before proceeding.", Status.BAD_REQUEST);
        }
        try {
            intYear = Integer.parseInt(year);
            int currentYear = LocalDate.now().getYear();
            if (intYear < currentYear || intYear > currentYear + 5) {
                return new Response("Please enter a valid year between " + currentYear + " and " + (currentYear + 5), Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Year must be a number", Status.BAD_REQUEST);
        }

        if (month.equals("Month")) {
            return new Response("You must choose a month before proceeding.", Status.BAD_REQUEST);
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
        } catch (NumberFormatException e) {
            return new Response("Day must be a number", Status.BAD_REQUEST);
        }
        int intHour, intMinutes;
        if (hour.equals("Hour")) {
            return new Response("You must choose a hour before proceeding.", Status.BAD_REQUEST);
        }
        if (minutes.equals("Minute")) {
            return new Response("You must choose a minute before proceeding.", Status.BAD_REQUEST);
        }
        intHour = Integer.parseInt(hour);
        intMinutes = Integer.parseInt(minutes);

        try {
            departureLocalDate = LocalDateTime.of(intYear, intMonth, intDay, intHour, intMinutes);
            if (departureLocalDate.isBefore(LocalDateTime.now())) {
                return new Response("Departure date cannot be from the past", Status.BAD_REQUEST);
            }
        } catch (DateTimeException e) {
            return new Response("Departure date is invalid or does not exist.", Status.BAD_REQUEST);
        }

        if (hoursArrivalStr.equals("Hour")) {
            return new Response("You must choose a hour before proceeding.", Status.BAD_REQUEST);
        }
        if (minutesArrivalStr.equals("Minute")) {
            return new Response("You must choose a minute before proceeding.", Status.BAD_REQUEST);
        }
        int hoursArrival = Integer.parseInt(hoursArrivalStr);
        int minutesArrival = Integer.parseInt(minutesArrivalStr);
        int hoursScale = 0;
        int minutesScale = 0;

        if (hoursArrival == 0 && minutesArrival == 0) {
            return new Response("The duration of the flight must be greater than 00:00.", Status.BAD_REQUEST);
        }

        Location scale = null;
        boolean hayEscala = (!scaleId.equals("Location"));

        if (hayEscala) {
            scale = LocationStorage.getInstance().get(scaleId);
            if (scale == null) {
                return new Response("Scale location does not exist.", Status.BAD_REQUEST);
            }
            if (hoursScaleStr.equals("Hour")) {
                return new Response("You must choose a scale hour before proceeding.", Status.BAD_REQUEST);
            }
            if (minutesScaleStr.equals("Minute")) {
                return new Response("You must choose a scale minute before proceeding.", Status.BAD_REQUEST);
            }
            hoursScale = Integer.parseInt(hoursScaleStr);
            minutesScale = Integer.parseInt(minutesScaleStr);
            if (hoursScale == 0 && minutesScale == 0) {
                return new Response("Scale time must be greater than 0 if a scale is specified.", Status.BAD_REQUEST);
            }
        } else {
            if (!hoursScaleStr.equals("Hour") || !minutesScaleStr.equals("Minute")) {
                return new Response("There can be no scale time if there is no scale location.", Status.BAD_REQUEST);
            }
        }
        if(departure == scale){
            return new Response("The departure and scale location cannot be the same", Status.BAD_REQUEST);
        }
        if(arrival == scale){
            return new Response("The arrival and scale location cannot be the same", Status.BAD_REQUEST);
        }
        Flight flight;
        if (hayEscala) {
            flight = new Flight(id, plane, departure, scale, arrival, departureLocalDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
        } else {
            flight = new Flight(id, plane, departure, arrival, departureLocalDate, hoursArrival, minutesArrival);
        }

        FlightStorage.getInstance().add(flight);
        return new Response("Flight added successfully.", Status.CREATED);
    }

    public static Response delayFlight(String flightId, String hour, String minutes) {
        int intHour, intMinutes;
        FlightCoordinator flightCoordinator = new FlightCoordinator();
        Flight flight = FlightStorage.getInstance().get(flightId);
        if (flight == null) {
            return new Response("The flight does not exist.", Status.BAD_REQUEST);
        }
        if (hour.equals("Hour")) {
            return new Response("You must choose a hour before proceeding.", Status.BAD_REQUEST);
        }
        if (minutes.equals("Minute")) {
            return new Response("You must choose a minute before proceeding.", Status.BAD_REQUEST);
        }
        intHour = Integer.parseInt(hour);
        intMinutes = Integer.parseInt(minutes);
        flightCoordinator.delay(flight, intHour, intMinutes);
        return new Response("The flight has been successfully delayed", Status.OK);

    }

    public static Response getFlightsWithFormat() {
        try {
            FlightFormatter formatter = new FlightFormatter();
            ArrayList<Flight> flights = (ArrayList<Flight>) FlightController.getAllFlights().getObject();
            ArrayList<String[]> data = new ArrayList<>();

            for (Flight flight : flights) {
                data.add(formatter.format(flight));
            }
            return new Response("Flights retrieved successfully.", Status.OK, data);
        } catch (Exception e) {
            return new Response("Error retrieving flights: ", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }

    }
}
