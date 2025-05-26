/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import core.controllers.PlaneController;
import core.controllers.utils.Response;
import core.models.Plane;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PlaneComboBoxObserver extends Observer {

    private JComboBox comboBox1;

    public PlaneComboBoxObserver(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    @Override
    public void notify(int value) {
        
        comboBox1.removeAllItems();
        Response response = PlaneController.getAllPlanes();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            comboBox1.addItem("Plane");
            for (Plane p : (ArrayList<Plane>) response.getObject()) {
                comboBox1.addItem(p.getId());
            }
        }
        
    }

}
