/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.reader;

import java.io.IOException;

/**
 *
 * @author User
 */
public interface Reader {

    Object read(String path) throws IOException;
    
}
