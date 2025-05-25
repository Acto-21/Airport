/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;
import core.models.storage.loaders.PlaneLoader;
import core.models.storage.reader.LineFileReader;
import core.services.formatters.PlaneFormatter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PlaneController {

    public static Response loadPlanesFromJson(String path) {
        try {
            PlaneStorage planes = PlaneStorage.getInstance();
            PlaneLoader loader = new PlaneLoader(planes);
            String jsonPlanes = LineFileReader.readFile(path);
            loader.loadFromFile(jsonPlanes);
            return new Response("Planes loaded successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Error loading planes: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAllPlanes() {
        ArrayList<Plane> originalList = PlaneStorage.getInstance().getAll();
        ArrayList<Plane> copiaList = new ArrayList<>();
        for (Plane avion : originalList) {
            try {
                copiaList.add(avion.clone());
            } catch (Exception e) {
                return new Response("Error cloning planes: ", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
            }
        }
        return new Response("Planes retrieved successfully.", Status.OK, copiaList);
    }

    public static Response addPlane(String id, String brand, String model, String maxCapacity, String airline) {
        PlaneStorage storage = PlaneStorage.getInstance();
        int intMaxCapacity;
        try {
            if (id.equals("")) {
                return new Response("ID must be not empty", Status.BAD_REQUEST);
            }
            if (id.length() != 7) {
                return new Response("Invalid ID: must be exactly 6 characters (2 letters followed by 5 numbers)", Status.BAD_REQUEST);
            }
            String idLetters = id.substring(0, 2);
            String idNumbers = id.substring(2, 7);
            try {
                Integer.parseInt(idLetters);
                return new Response("Invalid ID: first 2 digits must be capital letters", Status.BAD_REQUEST);
            } catch (NumberFormatException e) {
                if (idLetters.equals(idLetters.toLowerCase())) {
                    return new Response("Invalid ID: first 2 digits must be capital letters", Status.BAD_REQUEST);
                }
            }
            try {
                Integer.parseInt(idNumbers);
            } catch (NumberFormatException e) {
                return new Response("Invalid ID: last 5 digits must be numbers", Status.BAD_REQUEST);
            }
            if(storage.get(id) != null){
                return new Response("A plane already exists with that ID.", Status.BAD_REQUEST);
            }
            
            if (brand.equals("")) {
                return new Response("Brand must be not empty", Status.BAD_REQUEST);
            }
            if (model.equals("")) {
                return new Response("Model must be not empty", Status.BAD_REQUEST);
            }
            if (maxCapacity.equals("")) {
                return new Response("Max capacity must be not empty", Status.BAD_REQUEST);
            }
            try {
                intMaxCapacity = Integer.parseInt(maxCapacity);
            } catch (NumberFormatException e) {
                return new Response("Max capacity must be a numeric", Status.BAD_REQUEST);
            }
            if (airline.equals("")) {
                return new Response("Airline must be not empty", Status.BAD_REQUEST);
            }
            storage.add(new Plane(id, brand, model, intMaxCapacity, airline));
            return new Response("Plane created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getPlanesWithFormat(){
        try{
            PlaneFormatter formatter = new PlaneFormatter();
            ArrayList<Plane> planes = (ArrayList<Plane>) PlaneController.getAllPlanes().getObject();
            ArrayList<String[]> data = new ArrayList<>();
            for (Plane plane: planes){
                data.add(formatter.format(plane));
            }
            return new Response("Planes retrieved successfully.", Status.OK, data);
        }catch (Exception e){
            return new Response("Error retrieving planes: ", Status.INTERNAL_SERVER_ERROR, new ArrayList<>());
        }
    }
}
