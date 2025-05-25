/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.loaders;


/**
 *
 * @author User
 */
public interface DataLoader<T> {
    void loadFromFile(Object content);
}
