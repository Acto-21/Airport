/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.loaders;

import core.models.Flight;
import core.models.IFlight;
import core.models.ILocation;
import core.models.IPlane;
import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author User
 */
public class FlightJSonLoader implements DataLoader<Flight> {

    private final FlightStorage flights;
    private final PlaneStorage planes;
    private final LocationStorage locations;

    public FlightJSonLoader(FlightStorage flights, PlaneStorage planes, LocationStorage locations) {
        this.flights = flights;
        this.planes = planes;
        this.locations = locations;
    }

    @Override
    public void loadFromFile(Object data) {

        JSONArray jsonArray = new JSONArray((String)data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject flight = jsonArray.getJSONObject(i);

            String id = flight.getString("id");
            
            String planeId = flight.getString("plane");
            IPlane plane = this.planes.get(planeId);


            String departureLocationId = flight.getString("departureLocation");
            ILocation departureLocation = (ILocation) this.locations.get(departureLocationId);

            String arrivalLocationId = flight.getString("arrivalLocation");
            ILocation arrivalLocation = (ILocation) this.locations.get(arrivalLocationId);

            String departureDateString = flight.getString("departureDate");
            LocalDateTime departureDate = LocalDateTime.parse(departureDateString);

            int hoursDurationArrival = flight.getInt("hoursDurationArrival");
            int minutesDurationArrival = flight.getInt("minutesDurationArrival");
            int hoursDurationScale = flight.getInt("hoursDurationScale");
            int minutesDurationScale = flight.getInt("minutesDurationScale");

            IFlight newFlight;

            // Verifica si "scaleLocation" es null
            if (flight.isNull("scaleLocation")) {
                newFlight = new Flight(
                        id, plane, departureLocation, arrivalLocation,
                        departureDate, hoursDurationArrival, minutesDurationArrival
                );
            } else {
                String scaleLocationId = flight.getString("scaleLocation");
                ILocation scaleLocation = this.locations.get(scaleLocationId);
                newFlight = new Flight(
                        id, plane, departureLocation, (ILocation) scaleLocation, arrivalLocation,
                        departureDate, hoursDurationArrival, minutesDurationArrival,
                        hoursDurationScale, minutesDurationScale
                );
            }

            this.flights.add(newFlight);
        }
    }

}
