/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.Location;

/**
 *
 * @author User
 */
public class LocationDuplicateChecker implements DuplicateChecker<Location>{

    @Override
    public boolean isDuplicate(Location existing, Location candidate) {
        return existing.getAirportId().equals(candidate.getAirportId());
    }
    
}
