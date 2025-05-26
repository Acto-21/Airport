/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.services.duplicateChecker;

/**
 *
 * @author User
 */
public interface DuplicateChecker<T> {
    boolean isDuplicate(T existing, T candidate);
}
