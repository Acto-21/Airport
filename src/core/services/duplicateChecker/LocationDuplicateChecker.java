/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.ILocation;

/**
 *
 * @author User
 */
public class LocationDuplicateChecker implements DuplicateChecker<ILocation>{

    @Override
    public boolean isDuplicate(ILocation existing, ILocation candidate) {
        return existing.getAirportId().equals(candidate.getAirportId());
    }
    
}
