/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.Plane;

/**
 *
 * @author User
 */
public class PlaneDuplicateChecker implements DuplicateChecker<Plane>{

    @Override
    public boolean isDuplicate(Plane existing, Plane candidate) {
        return existing.getId().equals(candidate.getId());
    }
    
}
