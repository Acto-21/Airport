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
import core.models.storage.reader.JsonFileReader;
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
            String jsonPlanes = JsonFileReader.readFile(path);
            loader.loadFromFile(jsonPlanes);
            return new Response("Planes loaded successfully", Status.OK);
        }
        catch (Exception e) {
            return new Response("Error loading planes: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    } 
    
    public static Response getAllPlanes() {
        ArrayList<Plane> originalList = PlaneStorage.getInstance().getAll();
        ArrayList<Plane> copiaList = new ArrayList<>();
        for(Plane avion: originalList){
            try{
                copiaList.add((Plane) avion.clone());
            }catch(Exception e){
                return new Response("Error cloning planes: ", Status.INTERNAL_SERVER_ERROR,new ArrayList<>());
            }
        }
        return new Response("Planes retrieved successfully.", Status.OK, copiaList);
    }
    
}
