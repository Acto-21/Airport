/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.Flight;

/**
 *
 * @author User
 */
public class FlightDuplicateChecker implements DuplicateChecker<Flight> {
    
    @Override
    public boolean isDuplicate(Flight existing, Flight candidate) {
        return existing.getId().equals(candidate.getId());
    }
    
}
