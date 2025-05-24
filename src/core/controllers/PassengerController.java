/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import core.models.storage.loaders.PassengerLoader;
import core.models.storage.reader.JsonFileReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PassengerController {

    private PassengerStorage passengers;
    private PassengerLoader loader;

    public static Response loadPassengersFromJson(String path) {
        try {
            PassengerStorage passengers = PassengerStorage.getInstance();
            PassengerLoader loader = new PassengerLoader(passengers);
            String jsonPassengers = JsonFileReader.readFile(path);
            loader.loadFromFile(jsonPassengers);
            return new Response("Passengers loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error loading passengers: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllPassengers() {
        ArrayList<Passenger> originalList = PassengerStorage.getInstance().getAll();
        ArrayList<Passenger> copiaList = new ArrayList<>();
        for (Passenger pasajero : originalList) {
            try {
                copiaList.add((Passenger) pasajero.clone());
            } catch (Exception e) {
                return new Response("Error cloning passengers: ", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
            }
        }
        return new Response("Passengers retrieved successfully.", Status.OK, copiaList);
    }

    public static Response addPassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        PassengerStorage storage = PassengerStorage.getInstance();
        long longId;
        int intYear;
        int intMonth;
        int intDay;
        int intPhoneCode;
        long longPhone;
        LocalDate birthDate;
        try {
            if (id.equals("")) {
                return new Response("ID must be not empty", Status.BAD_REQUEST);
            }
            try {
                longId = Long.parseLong(id);
                if (longId < 0) {
                    return new Response("ID must be positive.", Status.BAD_REQUEST);
                }
                long maxId = 999999999999999L;
                if (longId > maxId) {
                    return new Response("ID cannot exceed 15 digit.", Status.BAD_REQUEST);
                }
                if (storage.get(id) != null) {
                    return new Response("A passenger already uses that ID.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            if (year.equals("")) {
                return new Response("Year must be not empty", Status.BAD_REQUEST);
            }
            if (month.equals("Month")) {
                return new Response("You must choose a month before proceeding.", Status.BAD_REQUEST);
            }
            try {
                    intYear = Integer.parseInt(year);
                    int currentYear = LocalDate.now().getYear();
                    if (intYear < 1900 || intYear > currentYear) {
                        return new Response("Please enter a valid birth year between 1900 and " + currentYear + ".", Status.BAD_REQUEST);
                    }
                    
                } catch (NumberFormatException e) {
                    return new Response("Birth year must be a number", Status.BAD_REQUEST);
                }
            intMonth = Integer.parseInt(month);
            if (day.equals("Day")) {
                return new Response("You must choose a day before proceeding.", Status.BAD_REQUEST);
            }
            intDay = Integer.parseInt(day);
            try {
                birthDate = LocalDate.of(intYear, intMonth, intDay);
            } catch (DateTimeException e) {
                return new Response("Birth date is invalid or does not exist.", Status.BAD_REQUEST);
            }
            if (countryPhoneCode.equals("")) {
                return new Response("Phone Code must be not empty", Status.BAD_REQUEST);
            }
            try {
                intPhoneCode = Integer.parseInt(countryPhoneCode);
                if (intPhoneCode < 0) {
                    return new Response("Phone code must be positive.", Status.BAD_REQUEST);
                }
                int maxPhoneCode = 999;
                if (intPhoneCode > maxPhoneCode) {
                    return new Response("Phone code cannot exceed 3 digit.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }
            if (phone.equals("")) {
                return new Response("Phone must be not empty", Status.BAD_REQUEST);
            }
            try {
                longPhone = Long.parseLong(phone);
                if (longPhone < 0) {
                    return new Response("Phone must be positive.", Status.BAD_REQUEST);
                }
                long maxPhone = 99999999999L;
                if (longPhone > maxPhone) {
                    return new Response("Phone cannot exceed 11 digit.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            storage.add(new Passenger(longId, firstname, lastname, birthDate, intPhoneCode, longPhone, country));
            return new Response("Passenger created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
    
    public static Response updatePassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        PassengerStorage storage = PassengerStorage.getInstance();
        long longId;
        int intYear;
        int intMonth;
        int intDay;
        int intPhoneCode;
        long longPhone;
        Passenger passenger;
        LocalDate birthDate;
        try {
            if (id.equals("")) {
                return new Response("User must be selected", Status.BAD_REQUEST);
            }
            try {
                longId = Long.parseLong(id);
                if (longId < 0) {
                    return new Response("ID must be positive.", Status.BAD_REQUEST);
                }
                long maxId = 999999999999999L;
                if (longId > maxId) {
                    return new Response("ID cannot exceed 15 digit.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }
            passenger = storage.get(id);
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            if (year.equals("")) {
                return new Response("Year must be not empty", Status.BAD_REQUEST);
            }
            if (month.equals("Month")) {
                return new Response("You must choose a month before proceeding.", Status.BAD_REQUEST);
            }
            try {
                intYear = Integer.parseInt(year);
                int currentYear = LocalDate.now().getYear();
                if (intYear < 1900 || intYear > currentYear) {
                    return new Response("Please enter a valid birth year between 1900 and " + currentYear + ".", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                return new Response("Birth year must be a number", Status.BAD_REQUEST);
            }
            intMonth = Integer.parseInt(month);
            if (day.equals("Day")) {
                return new Response("You must choose a day before proceeding.", Status.BAD_REQUEST);
            }
            intDay = Integer.parseInt(day);
            try {
                birthDate = LocalDate.of(intYear, intMonth, intDay);
            } catch (DateTimeException e) {
                return new Response("Birth date is invalid or does not exist.", Status.BAD_REQUEST);
            }
            if (countryPhoneCode.equals("")) {
                return new Response("Phone Code must be not empty", Status.BAD_REQUEST);
            }
            try {
                intPhoneCode = Integer.parseInt(countryPhoneCode);
                if (intPhoneCode < 0) {
                    return new Response("Phone code must be positive.", Status.BAD_REQUEST);
                }
                int maxPhoneCode = 999;
                if (intPhoneCode > maxPhoneCode) {
                    return new Response("Phone code cannot exceed 3 digit.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }
            if (phone.equals("")) {
                return new Response("Phone must be not empty", Status.BAD_REQUEST);
            }
            try {
                longPhone = Long.parseLong(phone);
                if (longPhone < 0) {
                    return new Response("Phone must be positive.", Status.BAD_REQUEST);
                }
                long maxPhone = 99999999999L;
                if (longPhone > maxPhone) {
                    return new Response("Phone cannot exceed 11 digit.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            passenger.setFirstname(firstname);
            passenger.setLastname(lastname);
            passenger.setBirthDate(birthDate);
            passenger.setCountryPhoneCode(intPhoneCode);
            passenger.setPhone(longPhone);
            passenger.setCountry(country);
            return new Response("Passenger data updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
