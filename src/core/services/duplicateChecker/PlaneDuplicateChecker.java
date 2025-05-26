/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

import core.models.IPlane;

/**
 *
 * @author User
 */
public class PlaneDuplicateChecker implements DuplicateChecker<IPlane>{

    @Override
    public boolean isDuplicate(IPlane existing, IPlane candidate) {
        return existing.getId().equals(candidate.getId());
    }
    
}
