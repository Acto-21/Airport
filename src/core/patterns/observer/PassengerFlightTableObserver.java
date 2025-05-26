/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.patterns.observer;

import core.controllers.PassengerController;
import core.controllers.utils.Response;
import core.models.Passenger;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class PassengerFlightTableObserver extends Observer {

    private DefaultTableModel tableModel;
    private Passenger currentUser = null;

    public PassengerFlightTableObserver(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public void notify(int value) {
        if (value == 3) {
            this.currentUser = UserManager.getInstance().getCurrentUser();
        }
        tableModel.setRowCount(0);
        Response response = PassengerController.showPassengerFlights(String.valueOf(this.currentUser.getId()));
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            for (String[] data : (ArrayList<String[]>) response.getObject()) {
                tableModel.addRow(data);
            }
        }
    }

}
