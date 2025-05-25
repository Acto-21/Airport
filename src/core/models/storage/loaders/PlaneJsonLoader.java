/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.loaders;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author User
 */
public class PlaneJsonLoader implements DataLoader<Plane>{
    
    private final PlaneStorage planes;

    public PlaneJsonLoader(PlaneStorage planes) {
        this.planes = planes;
    }
    
    @Override
    public void loadFromFile(Object data) {
        JSONArray jsonArray = new JSONArray((String)data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject plane = jsonArray.getJSONObject(i);
            
            String id = plane.getString("id");
            String brand = plane.getString("brand");
            String model = plane.getString("model");
            int maxCapacity = plane.getInt("maxCapacity");
            String airline = plane.getString("airline");
            
            Plane newPlane = new Plane(id,brand,model,maxCapacity,airline);
            this.planes.add(newPlane);
        }
    }
    
}
