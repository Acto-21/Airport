/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;


import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.IFlight;
import core.models.IPassenger;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;
import core.models.storage.loaders.PassengerLoader;
import core.models.storage.reader.LineFileReader;
import core.patterns.observer.UserManager;
import core.services.PassengerOrder;
import core.services.PassengerManager;
import core.services.formatters.PassengerFlightFormatter;
import core.services.formatters.PassengerFormatter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PassengerController {

    public static Response loadPassengersFromJson(String path) {
        try {
            PassengerStorage passengers = PassengerStorage.getInstance();
            PassengerLoader loader = new PassengerLoader(passengers);
            String jsonPassengers = LineFileReader.readFile(path);
            loader.loadFromFile(jsonPassengers);
            return new Response("Passengers loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error loading passengers: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllPassengers() {
        try {
            ArrayList<IPassenger> originalList = new ArrayList<>(PassengerStorage.getInstance().getAll());
            ArrayList<IPassenger> orderedList = PassengerOrder.order(originalList);
            return new Response("Passengers retrieved successfully.", Status.OK, orderedList);
        } catch (Exception e) {
            return new Response("Error retrieving passengers", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }
    }

    public static Response addPassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        try {
            PassengerStorage storage = PassengerStorage.getInstance();
            long longId;
            int intYear, intMonth, intDay, intPhoneCode;
            long longPhone;
            LocalDate birthDate;

            if (id.isEmpty()) return new Response("ID must be not empty", Status.BAD_REQUEST);
            try {
                longId = Long.parseLong(id);
                if (longId < 0) return new Response("ID must be positive.", Status.BAD_REQUEST);
                if (longId > 999999999999999L) return new Response("ID cannot exceed 15 digit.", Status.BAD_REQUEST);
                if (storage.get(id) != null) return new Response("A passenger already uses that ID.", Status.BAD_REQUEST);
            } catch (NumberFormatException ex) {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.isEmpty()) return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            if (lastname.isEmpty()) return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            if (year.isEmpty()) return new Response("Year must be not empty", Status.BAD_REQUEST);
            if (month.equals("Month")) return new Response("You must choose a month before proceeding.", Status.BAD_REQUEST);
            if (day.equals("Day")) return new Response("You must choose a day before proceeding.", Status.BAD_REQUEST);

            try {
                intYear = Integer.parseInt(year);
                int currentYear = LocalDate.now().getYear();
                if (intYear < 1900 || intYear > currentYear)
                    return new Response("Please enter a valid birth year between 1900 and " + currentYear + ".", Status.BAD_REQUEST);
            } catch (NumberFormatException e) {
                return new Response("Birth year must be a number", Status.BAD_REQUEST);
            }

            intMonth = Integer.parseInt(month);
            intDay = Integer.parseInt(day);

            try {
                birthDate = LocalDate.of(intYear, intMonth, intDay);
            } catch (DateTimeException e) {
                return new Response("Birth date is invalid or does not exist.", Status.BAD_REQUEST);
            }

            if (countryPhoneCode.isEmpty()) return new Response("Phone Code must be not empty", Status.BAD_REQUEST);
            try {
                intPhoneCode = Integer.parseInt(countryPhoneCode);
                if (intPhoneCode < 0) return new Response("Phone code must be positive.", Status.BAD_REQUEST);
                if (intPhoneCode > 999) return new Response("Phone code cannot exceed 3 digit.", Status.BAD_REQUEST);
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }

            if (phone.isEmpty()) return new Response("Phone must be not empty", Status.BAD_REQUEST);
            try {
                longPhone = Long.parseLong(phone);
                if (longPhone < 0) return new Response("Phone must be positive.", Status.BAD_REQUEST);
                if (longPhone > 99999999999L) return new Response("Phone cannot exceed 11 digit.", Status.BAD_REQUEST);
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }

            if (country.isEmpty()) return new Response("Country must be not empty", Status.BAD_REQUEST);

            storage.add((IPassenger) new Passenger(longId, firstname, lastname, birthDate, intPhoneCode, longPhone, country));
            return new Response("Passenger created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response updatePassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        try {
            PassengerStorage storage = PassengerStorage.getInstance();
            long longId;
            int intYear, intMonth, intDay, intPhoneCode;
            long longPhone;
            LocalDate birthDate;

            if (id.isEmpty()) return new Response("User must be selected", Status.BAD_REQUEST);
            try {
                longId = Long.parseLong(id);
                if (longId < 0) return new Response("ID must be positive.", Status.BAD_REQUEST);
                if (longId > 999999999999999L) return new Response("ID cannot exceed 15 digit.", Status.BAD_REQUEST);
            } catch (NumberFormatException ex) {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }

            if (storage.get(id) == null) return new Response("Passenger not found", Status.BAD_REQUEST);
            if (firstname.isEmpty()) return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            if (lastname.isEmpty()) return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            if (year.isEmpty()) return new Response("Year must be not empty", Status.BAD_REQUEST);
            if (month.equals("Month")) return new Response("You must choose a month before proceeding.", Status.BAD_REQUEST);
            if (day.equals("Day")) return new Response("You must choose a day before proceeding.", Status.BAD_REQUEST);

            try {
                intYear = Integer.parseInt(year);
                int currentYear = LocalDate.now().getYear();
                if (intYear < 1900 || intYear > currentYear)
                    return new Response("Please enter a valid birth year between 1900 and " + currentYear + ".", Status.BAD_REQUEST);
            } catch (NumberFormatException e) {
                return new Response("Birth year must be a number", Status.BAD_REQUEST);
            }

            intMonth = Integer.parseInt(month);
            intDay = Integer.parseInt(day);
            birthDate = LocalDate.of(intYear, intMonth, intDay);

            if (countryPhoneCode.isEmpty()) return new Response("Phone Code must be not empty", Status.BAD_REQUEST);
            intPhoneCode = Integer.parseInt(countryPhoneCode);
            if (intPhoneCode < 0 || intPhoneCode > 999) return new Response("Phone code must be positive and max 3 digits", Status.BAD_REQUEST);

            if (phone.isEmpty()) return new Response("Phone must be not empty", Status.BAD_REQUEST);
            longPhone = Long.parseLong(phone);
            if (longPhone < 0 || longPhone > 99999999999L) return new Response("Phone must be positive and max 11 digits", Status.BAD_REQUEST);

            if (country.isEmpty()) return new Response("Country must be not empty", Status.BAD_REQUEST);

            Passenger updated = new Passenger(longId, firstname, lastname, birthDate, intPhoneCode, longPhone, country);
            if (!storage.update((IPassenger) updated)) {
                return new Response("Passenger not found in database", Status.INTERNAL_SERVER_ERROR);
            }

            return new Response("Passenger data updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response addToFlight(String passengerId, String flightId) {
        try {
            if (passengerId.isEmpty()) return new Response("User must be selected", Status.BAD_REQUEST);
            if (flightId.equals("Flight")) return new Response("Flight must be selected", Status.BAD_REQUEST);

            Passenger passenger = (Passenger) PassengerStorage.getInstance().get(passengerId);
            IFlight flight = FlightStorage.getInstance().get(flightId);

            if (passenger == null) return new Response("Passenger with selected ID not found", Status.BAD_REQUEST);
            if (flight == null) return new Response("Flight with selected ID not found", Status.BAD_REQUEST);
            if (flight.getNumPassengers() >= flight.getPlane().getMaxCapacity())
                return new Response("Flight is full. Cannot add more passengers.", Status.BAD_REQUEST);

            new PassengerManager().addPassenger(flight, (IPassenger) passenger);
            return new Response("Passenger added to flight", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response showPassengerFlights(String passengerId) {
        try {
            Passenger passenger = (Passenger) PassengerStorage.getInstance().get(passengerId);
            if (passenger == null) return new Response("Passenger not found in Database", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());

            ArrayList<IFlight> flights = (ArrayList<IFlight>) passenger.getFlights();
            if (flights.isEmpty()) return new Response("Passenger has no flights", Status.OK, new ArrayList<>());

            PassengerFlightFormatter formatter = new PassengerFlightFormatter();
            ArrayList<String[]> data = new ArrayList<>();
            for (IFlight flight : flights) {
                data.add(formatter.format((Flight) flight));
            }

            return new Response("Passenger flights retrieved successfully.", Status.OK, data);
        } catch (Exception e) {
            return new Response("Error retrieving passenger flights", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }
    }

    public static Response getPassengersWithFormat() {
        try {
            PassengerFormatter formatter = new PassengerFormatter();
            ArrayList<IPassenger> passengers = (ArrayList<IPassenger>) getAllPassengers().getObject();
            ArrayList<String[]> data = new ArrayList<>();
            for (IPassenger passenger : passengers) {
                data.add(formatter.format((Passenger) passenger));
            }
            return new Response("Passengers retrieved successfully.", Status.OK, data);
        } catch (Exception e) {
            return new Response("Error retrieving passengers", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }
    }

    public static Response changeUser(String id) {
        try {
            if (id.equals("Select User")) return new Response("Please select a user first", Status.BAD_REQUEST);

            Passenger passenger = (Passenger) PassengerStorage.getInstance().get(id);
            if (passenger == null) return new Response("Passenger with selected ID not found", Status.BAD_REQUEST);

            UserManager.getInstance().setCurrentUser(passenger);
            return new Response("User successfully changed", Status.OK);
        } catch (Exception e) {
            return new Response("Error selecting user", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }
    }
}
