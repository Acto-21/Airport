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
        }catch (Exception e) {
            return new Response("Error loading passengers: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getAllPassengers() {
        ArrayList<Passenger> originalList = PassengerStorage.getInstance().getAll();
        ArrayList<Passenger> copiaList = new ArrayList<>();
        for(Passenger pasajero: originalList){
            try{
                copiaList.add((Passenger) pasajero.clone());
            }catch(Exception e){
                return new Response("Error cloning passengers: ", Status.INTERNAL_SERVER_ERROR,new ArrayList<>());
            }
        }
        return new Response("Passengers retrieved successfully.", Status.OK, copiaList);
    }
    
}
