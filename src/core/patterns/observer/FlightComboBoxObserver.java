/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import core.controllers.FlightController;
import core.controllers.utils.Response;
import core.models.Flight;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FlightComboBoxObserver extends Observer {

    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public FlightComboBoxObserver(JComboBox comboBox1, JComboBox comboBox2) {
        this.comboBox1 = comboBox1;
        this.comboBox2 = comboBox2;
    }

    @Override
    public void notify(int value) {
        //Solamente se actualiza en caso de que se añada un vuelo
        if (value == 1) {
            comboBox1.removeAllItems();
            comboBox2.removeAllItems();
            Response response = FlightController.getAllFlights();
            if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                comboBox1.addItem("Flight");
                comboBox2.addItem("ID");
                for (Flight f : (ArrayList<Flight>) response.getObject()) {
                    comboBox1.addItem(f.getId());
                    comboBox2.addItem(f.getId());
                }
            }
        }
    }

}
