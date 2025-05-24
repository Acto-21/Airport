/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.formatters;

import core.models.Passenger;

/**
 *
 * @author User
 */
public class PhoneFormatter implements Formatter<Passenger>{

    @Override
    public String[] format(Passenger object) {
        String fullPhone = "+" + object.getCountryPhoneCode() + " " + object.getPhone();
        return new String[] { fullPhone };
    }
    
}
