/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import core.controllers.LocationController;
import core.controllers.utils.Response;
import core.models.Location;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class LocationComboBoxObserver extends Observer {

    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    public LocationComboBoxObserver(JComboBox comboBox1,JComboBox comboBox2,JComboBox comboBox3) {
        this.comboBox1 = comboBox1;
        this.comboBox2 = comboBox2;
        this.comboBox3 = comboBox3;
    }

    @Override
    public void notify(int value) {
        
        comboBox1.removeAllItems();
        comboBox2.removeAllItems();
        comboBox3.removeAllItems();
        Response response = LocationController.getAllLocations();
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            comboBox1.addItem("Location");
            comboBox1.addItem("Location");
            comboBox1.addItem("Location");
            for (Location f : (ArrayList<Location>) response.getObject()) {
                comboBox1.addItem(f.getAirportId());
                comboBox2.addItem(f.getAirportId());
                comboBox3.addItem(f.getAirportId());
            }
        }

    }
}
