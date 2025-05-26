/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.Passenger;

/**
 *
 * @author User
 */
public class PassengerDuplicateChecker implements DuplicateChecker<Passenger> {

    @Override
    public boolean isDuplicate(Passenger existing, Passenger candidate) {
         return existing.getId() == candidate.getId();
    }
    
}
