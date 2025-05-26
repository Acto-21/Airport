/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.IFlight;

/**
 *
 * @author User
 */
public class FlightDuplicateChecker implements DuplicateChecker<IFlight> {
    
    @Override
    public boolean isDuplicate(IFlight existing, IFlight candidate) {
        return existing.getId().equals(candidate.getId());
    }
    
}
