/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.loaders;

import java.util.List;

/**
 *
 * @author User
 */
public interface JsonDataLoader<T> {
    void loadFromFile(String jsonStr);
}
