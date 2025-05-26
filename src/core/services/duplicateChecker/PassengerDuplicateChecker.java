/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.IPassenger;

/**
 *
 * @author User
 */
public class PassengerDuplicateChecker implements DuplicateChecker<IPassenger> {

    @Override
    public boolean isDuplicate(IPassenger existing, IPassenger candidate) {
         return existing.getId() == candidate.getId();
    }
    
}
