/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import core.controllers.PassengerController;
import core.controllers.utils.Response;
import core.models.Passenger;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PassengerComboBoxObserver extends Observer {
    
    private JComboBox comboBox1;
    
    public PassengerComboBoxObserver(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }
    @Override
    public void notify(int value) {
        
        if (value == 1) {
            comboBox1.removeAllItems();
            Response response = PassengerController.getAllPassengers();
            if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                comboBox1.addItem("Select User");
                for (Passenger p : (ArrayList<Passenger>) response.getObject()) {
                    System.out.println("" + p.getId());
                    comboBox1.addItem("" +p.getId());
                }
            }
        }
    }
    
}
