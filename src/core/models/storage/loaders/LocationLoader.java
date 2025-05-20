/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.loaders;

import core.models.Location;
import core.models.storage.LocationStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author User
 */
public class LocationLoader implements JsonDataLoader<Location>  {
    
    private final LocationStorage locations;

    public LocationLoader(LocationStorage locations) {
        this.locations = locations;
    }
    
    @Override
    public void loadFromFile(String jsonStr) {
        JSONArray jsonArray = new JSONArray(jsonStr);
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject location = jsonArray.getJSONObject(i);
            
            String airportId = location.getString("airportId");
            String airportName = location.getString("airportName");
            String airportCity = location.getString("airportCity");
            String airportCountry = location.getString("airportCountry");
            
            double airportLatitude = location.getDouble("airportLatitude");
            double airportLongitude = location.getDouble("airportLongitude");
            
            Location newLocation = new Location(airportId,airportName,airportCity,airportCountry,airportLatitude,airportLongitude);
            this.locations.add(newLocation);
        }
    }
    
}
