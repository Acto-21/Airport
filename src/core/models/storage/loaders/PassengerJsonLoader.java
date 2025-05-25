/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.loaders;

import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author User
 */
public class PassengerJsonLoader implements DataLoader<Passenger>{

    private final PassengerStorage passengers;

    public PassengerJsonLoader(PassengerStorage passengers) {
        this.passengers = passengers;
    }
    
    
    @Override
    public void loadFromFile(Object data) {
        JSONArray jsonArray = new JSONArray((String)data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject passenger = jsonArray.getJSONObject(i);
            
            long id = passenger.getLong("id");
            String firstname = passenger.getString("firstname");
            String lastname = passenger.getString("lastname");
            String birthDateString = passenger.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(birthDateString);
            int countryPhoneCode = passenger.getInt("countryPhoneCode");
            long phone = passenger.getLong("phone");
            String country = passenger.getString("country");
            
            Passenger newPassenger = new Passenger(id,firstname,lastname,birthDate,countryPhoneCode,phone,country);
            this.passengers.add(newPassenger);
        }
    }
    
}
