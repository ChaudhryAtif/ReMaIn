import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ManagerDisplay extends JFrame {
    private JPanel dayInfo = new JPanel(), control = new JPanel();                  // Top (dayInfo), Bottom (control)

    // Inventory
    private boolean selectAll = false;
    private JFrame inventoryFrame = new JFrame();
    private JPanel inventoryPanel = new JPanel();
    private JPanel inventoryClock = new JPanel();
    private JPanel inventoryCtrls = new JPanel();
    private JTable inventoryTable;
    private JButton orderItem, cancelItem, checkAll;                     // Inventory Table Buttons

    private JLabel timeDate = new JLabel(), timeDateInvtry = new JLabel();          // timeDate: Dynamic Time & Date;

    private JButton cButton, iButton;
    private ButtonListener click = new ButtonListener();
    private PasswordVerifier pwdVerifier = new PasswordVerifier();

    public static final String[] users = { "Manager", "Cook", "Host", "Waiter" };
    private String[] passData;                                                      // Stores secured passwords


    public ManagerDisplay() {
        setupMDisplay();
    }

    private void setupMDisplay() {
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, "Welcome, Manager!", timeDate, .09, false);

        //*********************************************************************//
        // Add, and Update Looks of Buttons and Main JFrame
        //*********************************************************************//
        control.setLayout(new GridLayout(3, 1));
        JPanel manButtons = new JPanel(new GridLayout());

        Utilities.multiUpdateFont(.065, cButton = new JButton("User Controls"), iButton = new JButton("Inventory"));
        Utilities.multiAdd(manButtons, cButton, iButton);
        control.add(manButtons);
        add(control, BorderLayout.CENTER);

        //*********************************************************************//
        // Inventory Frame
        //*********************************************************************//
        Utilities.startDayInfo(inventoryFrame, inventoryClock, "Welcome, Manager!", timeDateInvtry, .09, false);
        inventoryPanel.setLayout(new BorderLayout());
        Utilities.multiUpdateFont(.03, checkAll = new JButton("Select All/None"),
                cancelItem = new JButton("Cancel Item(s)"), orderItem = new JButton("Order Item(s)"));
        Utilities.multiAdd(inventoryCtrls, checkAll, Box.createRigidArea(new Dimension(10, 0)),
                cancelItem, Box.createRigidArea(new Dimension(10, 0)), orderItem);

        //*********************************************************************//
        // Create, Populate, and Update Looks of Inventory Table
        //*********************************************************************//
        Object[][] inventoryData = InventoryManager.getInventoryItems();
        /*Object[][] inventoryData = {
                {"001", "Eggs", "20", "10", "Urgent", "PENDING", false},
                {"002", "Cheese", "05", "01", "", "ORDERED", false},
                {"003", "Bagels", "25", "03", "ORDER or DIE", "PENDING", false},
                {"004", "Bread", "25", "15", "Take Yo Time", "CANCELED", false}
        };*/
        Object[] inventoryColumns = {"Order ID", "Item Description", "Quantity Needed",
                "Quantity In Stock","Status", "Notes", "Select Item", "Order Quantity"};

        inventoryTable = new JTable() {
            @Override
            public Class<?> getColumnClass(int col) {
                if (col == 6) { return Boolean.class; }
                return super.getColumnClass(col);
            }
            @Override
            public boolean isCellEditable(int row, int col) {               // Make specific columns editable
                if (col == 6)
                    return true;
                if (col == 7)
                    return ((Boolean) getValueAt(row, 6)).booleanValue();
                else
                    return false;
            }
        };
        inventoryTable.setDefaultRenderer(Object.class, new TableRowRender());
        inventoryTable.setModel(new DefaultTableModel(inventoryData, inventoryColumns));

        inventoryPanel.add(inventoryCtrls, BorderLayout.NORTH);
        inventoryPanel.add(new JScrollPane(inventoryTable), BorderLayout.CENTER);
        inventoryFrame.add(inventoryPanel, BorderLayout.CENTER);

        // Listeners for all the Buttons;
        cButton.addActionListener(click);
        iButton.addActionListener(click);
        checkAll.addActionListener(click);
        cancelItem.addActionListener(click);
        orderItem.addActionListener(click);

        /** Set Visible Last To Avoid Glitches/Flickering **/
        setVisible(true);
        setResizable(false);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == iButton) {
                inventoryFrame.setVisible(true);
                inventoryFrame.setResizable(false);
            }
            if (event.getSource() == checkAll) {
                int row = inventoryTable.getRowCount();

                selectAll = !selectAll;
                for (int line = 0; line < row; line++) {
                    inventoryTable.setValueAt(selectAll, line, 6);
                }
                inventoryTable.clearSelection();
            }
            if (event.getSource() == cancelItem) {
                int row = inventoryTable.getRowCount();
                boolean check;

                for (int line = 0; line < row; line++) {
                    check = (Boolean) inventoryTable.getValueAt(line, 6);

                    if (check) {
                        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                        String status = "CANCELED";
                        model.setValueAt(status, line, 5);
                        InventoryManager.setStatus((String) model.getValueAt(inventoryTable.getSelectedRow(), 0), status);
                    }
                }
                selectAll = true;
                checkAll.doClick();
                inventoryTable.clearSelection();
            }
            if (event.getSource() == orderItem) {
                int row = inventoryTable.getRowCount();
                boolean check;

                for (int line = 0; line < row; line++) {
                    check = (Boolean) inventoryTable.getValueAt(line, 6);

                    if (check) {
                        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                        Object value = model.getValueAt(line, 7);
                        if (value != null || value == "") {
                            System.out.println(value.toString());
                        } else {                                                    // If no Qty given , take ordered Qty
                            model.setValueAt(model.getValueAt(line, 2), line, 7);
                            System.out.println(model.getValueAt(line, 2).toString());
                        }
                        String status = "ORDERED";
                        model.setValueAt(status, line, 5);
                        InventoryManager.setStatus((String) model.getValueAt(inventoryTable.getSelectedRow(), 0), status);
                    }
                }
                selectAll = true;
                checkAll.doClick();
                inventoryTable.clearSelection();
            }

            if (event.getSource() == cButton) {
                passData = pwdVerifier.getData(new File("UserPass.txt")); // Manager, Cook, Host, Waiter
                if (pwdVerifier.verifyPwd("manager")) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, 1));
                    panel.add(new JLabel("Please choose a user:"));
                    JComboBox userList = new JComboBox(users);

                    JPasswordField newPassA = new JPasswordField();
                    newPassA.setDocument(new InputLimit(10));
                    newPassA.setBorder(BorderFactory.createTitledBorder("Enter New Password (10 max):"));
                    JPasswordField newPassB = new JPasswordField();
                    newPassB.setDocument(new InputLimit(10));
                    newPassB.setBorder(BorderFactory.createTitledBorder("Repeat New Password (10 max):"));


                    Utilities.multiAdd(panel, userList, Box.createRigidArea(new Dimension(0,25)),
                            newPassA, Box.createRigidArea(new Dimension(0,5)), newPassB);

                    int reply = JOptionPane.showConfirmDialog(null,
                            panel,
                            "Password Changer",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                    if (reply == JOptionPane.OK_OPTION) {
                        if (!Arrays.equals(newPassA.getPassword(), newPassB.getPassword())) {
                            JOptionPane.showMessageDialog(null, "Passwords do not match!\nUser password NOT updated!", "Failure!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            passData[userList.getSelectedIndex()] = pwdVerifier.getSecurePass(newPassB.getPassword());
                            File file = new File("UserPass.txt");
                            try {
                                FileWriter writer = new FileWriter(file, false);

                                writer.write(passData[0]);
                                for (int i=1; i < passData.length; i++) { writer.write("," + passData[i]); }
                                writer.close();

                            } catch (IOException e) { e.printStackTrace(); }
                            JOptionPane.showMessageDialog(null, "Passwords matched\nUser password updated!!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } // Reply Check
                } // Password
            } // Control Button
        } // Action Event
    } // Button Listener

//    public static void main(String[] args) {
//        new ManagerDisplay();
//    }
}
