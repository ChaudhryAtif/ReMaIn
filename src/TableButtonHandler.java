import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Class to handle Table's button actions
 */
public class TableButtonHandler {
    //*********************************************************************//
    // [GENERAL] Tables' General Button Actions
    //*********************************************************************//

    /**
     * Checks if the given table is empty
     * @param table     JTable to check empty status of
     * @param tableMsg  JLabel to display text in if not empty
     * @return          Status of JTable (True/False)
     */
    public boolean isTableEmpty(JTable table, JLabel tableMsg) {
        tableMsg.setText("");
        if (table.getSelectedRow() == -1) {
            if (table.getRowCount() == 0) {
                tableMsg.setText("Table is Empty!");
                return false;
            } else {
                tableMsg.setText("You must select an order!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given input fields (of a Table) are empty
     * @param inputFields   JTextfield(s) to check empty status of
     * @param tableMsg      JLabel to display text in if not empty
     * @return              Status of JTextfields (True/False)
     */
    public boolean isInputEmpty(JLabel tableMsg, JTextField... inputFields) {
        tableMsg.setText("");
        for (JTextField field : inputFields) {
            if (field.getText().replaceAll("[ ,.:]","").equals("")) {
                tableMsg.setText("All input fields must be filled!");
                return true;
            }
        }
        return false;
    }

    /**
     * Clears the selection, inputFields (and tableMsg) of the given table
     * @param table         JTable to clear the fields of
     * @param tableMsg      JLabel to clear
     * @param inputFields   JTextfield(s) to be cleared
     */
    public void clearTableInput(JTable table, JLabel tableMsg, JTextField... inputFields) {
        table.clearSelection();
        tableMsg.setText("");
        for (JTextField field : inputFields) {
            field.setText("");
        }
    }

    /**
     * Takes value from selected row and sets it to inputFields accordingly
     * @param table         JTable to set the fields of
     * @param inputFields   JTextfield(s) to get the values of
     */
    public void setTableInput(JTable table, JTextField... inputFields) {
        int col = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (JTextField field : inputFields) {
            field.setText(model.getValueAt(table.getSelectedRow(), col).toString());
            col++;
        }
    }

    //*********************************************************************//
    // [COOK] Order Table's Button Actions
    //*********************************************************************//

    /**
     * Changes color of selected row to White. Sets Status column to 'NEW' (Default)
     * @param table         JTable of the selected row
     * @param tableMsg      JLabel (to use to check isTableEmpty)
     */
    public void resetOrder(JTable table, JLabel tableMsg) {
        if (!isTableEmpty(table, tableMsg)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String ID = (String) model.getValueAt(table.getSelectedRow(), 0);
            String status = "NEW";
            model.setValueAt(status, table.getSelectedRow(), 5);
            OrderManager.setStatus(ID, status);
            table.clearSelection();
        }
    }

    /**
     * Changes color of selected row to Yellow. Sets Status column to 'STARTED'
     * @param table         JTable of the selected row
     * @param tableMsg      JLabel (to use to check isTableEmpty)
     */
    public void startOrder(JTable table, JLabel tableMsg) {
        if (!isTableEmpty(table, tableMsg)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String ID = (String) model.getValueAt(table.getSelectedRow(), 0);
            String status = "STARTED";
            model.setValueAt(status, table.getSelectedRow(), 5);
            OrderManager.setStatus(ID, status);
            table.clearSelection();
        }
    }

    /**
     * Changes color of selected row to Green. Sets Status column to 'READY'
     * @param table         JTable of the selected row
     * @param tableMsg      JLabel (to use to check isTableEmpty)
     */
    public void readyOrder(JTable table, JLabel tableMsg) {
        if (!isTableEmpty(table, tableMsg)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String ID = (String) model.getValueAt(table.getSelectedRow(), 0);
            String status = "READY";
            model.setValueAt(status, table.getSelectedRow(), 5);
            OrderManager.setStatus(ID, status);
            table.clearSelection();
        }
    }

    /**
     * Changes color of selected row to Red. Sets Status column to 'HELP'
     * @param table         JTable of the selected row
     * @param tableMsg      JLabel (to use to check isTableEmpty)
     */
    public void helpOrder(JTable table, JLabel tableMsg) {
        if (!isTableEmpty(table, tableMsg)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String ID = (String) model.getValueAt(table.getSelectedRow(), 0);
            String status = "HELP";
            model.setValueAt(status, table.getSelectedRow(), 5);
            OrderManager.setStatus(ID, status);
            table.clearSelection();
        }
    }

    //*********************************************************************//
    // [COOK/WAITER] Order & Inventory Tables' Button Actions
    //*********************************************************************//

    /**
     * Adds a row to the table, with given inputField values
     * @param table         JTable to add the row to
     * @param tableName     JLabel to check if it's order or inventory table (for status)
     * @param inputFields   JTextField(s) to get the value from
     */
    public void addRow(JTable table, JLabel tableName, JTextField... inputFields) {
        int col = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String tableValues[] = new String[inputFields.length + 1];
        for (JTextField field : inputFields) {
            if ((col == 0) && (tableName.getText().equals("orderTable") || tableName.getText().equals("inventoryTable"))) {
                tableValues[col] = String.format("%03d", Integer.parseInt(field.getText()));
            } else {
                tableValues[col] = field.getText();
            }
            col++;
        }
        if (tableName.getText().equals("orderTable")) {
            tableValues[col] = "NEW";
            OrderManager.addOrder(tableValues);
        } else {
            tableValues[col] = "PENDING";
            InventoryManager.addInventoryItem(tableValues);
        }

        model.addRow(tableValues);
    }

    /**
     * Gets and Updates values of the selected table's row from the inputFields
     * @param table         JTable of the selected row
     * @param tableMsg      JLabel (to use for isTableEmpty)
     * @param tableName     JLabel to check if it's order or inventory table (for status)
     * @param inputFields   JTextField(s) to get the value from
     */
    public void updateRow(JTable table, JLabel tableMsg, JLabel tableName, JTextField... inputFields) {
        if (!isTableEmpty(table, tableMsg)) {
            int col = 0;
            String ID = "";
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String tableValues[] = new String[inputFields.length + 1];
            for (JTextField field : inputFields) {
            	if (col == 0) { ID = field.getText(); }
                model.setValueAt(field.getText(), table.getSelectedRow(), col);
                tableValues[col] = field.getText();
                col++;
            }
            
            if (ID != "" ) {
            	if (tableName.getText().equals("orderTable")) {
            		OrderManager.updateOrder(ID, tableValues);
            	}
            	else {
            		InventoryManager.updateItem(ID, tableValues);
            	}
            }
        }
    }

    /**
     * Adds a row to the table, with given information; Clears fields and selection when done
     * @param table         JTable to add the row to
     * @param tableMsg      JLabel to report any error
     * @param waiterBox     List of waiter names
     * @param statusBox     List of food statuses
     * @param tableNo       Table No
     * @param headServing   Head count
     * @param notes         Additional Notes
     */
    public void addFood(JTable table, JLabel tableMsg, JComboBox waiterBox, JComboBox statusBox,
                           JTextField tableNo, JTextField headServing, JTextField notes) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] tableValues = {
                waiterBox.getSelectedItem().toString(),
                tableNo.getText(),
                headServing.getText(),
                notes.getText(),
                statusBox.getSelectedItem().toString()
        };
        model.addRow(tableValues);

        clearFood(table, tableMsg, waiterBox, statusBox, tableNo, headServing, notes);
    }

    /**
     * Gets and Updates values of the selected table's row from given fields; Clears fields and selection when done
     * @param table         JTable to add the row to
     * @param tableMsg      JLabel to report any error
     * @param waiterBox     List of waiter names
     * @param statusBox     List of food statuses
     * @param tableNo       Table No
     * @param headServing   Head count
     * @param notes         Additional Notes
     */
    public void updateFood(JTable table, JLabel tableMsg, JComboBox waiterBox, JComboBox statusBox,
                           JTextField tableNo, JTextField headServing, JTextField notes) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(waiterBox.getSelectedItem(), table.getSelectedRow(), 0);
        model.setValueAt(tableNo.getText(), table.getSelectedRow(), 1);
        model.setValueAt(headServing.getText(), table.getSelectedRow(), 2);
        model.setValueAt(notes.getText(), table.getSelectedRow(), 3);
        model.setValueAt(statusBox.getSelectedItem(), table.getSelectedRow(), 4);

        clearFood(table, tableMsg, waiterBox, statusBox, tableNo, headServing, notes);
    }

    /**
     * Removes the selected row from the table
     * @param table         JTable of the selected row
     * @param tableMsg      JLabel (to use for isTableEmpty)
     * @param tableName     JLabel to check if it's order or inventory table (for status)
     */
    public void removeRow(JTable table, JLabel tableMsg, JLabel tableName) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        String ID = (String) model.getValueAt(table.getSelectedRow(), 0);
        if (tableName.getText().equals("orderTable")) {
        	OrderManager.removeOrder(ID);
        }
        else {
        	InventoryManager.removeInventoryItem(ID);
        }
        model.removeRow(table.getSelectedRow());
    }
	
    /**
     * Clears the selection, inputFields (and tableMsg) of the given table
     * @param table         JTable to add the row to
     * @param tableMsg      JLabel to report any error
     * @param waiterBox     List of waiter names
     * @param statusBox     List of food statuses
     * @param inputFields   JTextfield(s) to be cleared
     */
    public void clearFood(JTable table, JLabel tableMsg, JComboBox waiterBox, JComboBox statusBox, JTextField... inputFields) {
        tableMsg.setText("");
        table.clearSelection();
        waiterBox.setSelectedIndex(0);
        statusBox.setSelectedIndex(0);
        for (JTextField field : inputFields) {
            field.setText("");
        }
    }
}
