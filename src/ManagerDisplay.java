import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerDisplay extends JFrame {
    private JPanel dayInfo = new JPanel(), control = new JPanel();                  // Top (dayInfo), Bottom (control) Sections

    // Inventory
    private boolean selectAll = false;
    private JFrame inventoryFrame = new JFrame();
    private JPanel inventoryPanel = new JPanel();
    private JPanel inventoryClock = new JPanel();
    private JPanel inventoryCtrls = new JPanel();
    private JTable inventoryTable;
    private JButton orderItem, updateItm, removeItm, checkAll;                         // Inventory Table Buttons

    private JLabel timeDate = new JLabel(), timeDateInvtry = new JLabel();          // Dynamic Time & Date;

    private JButton rButton, cButton, iButton;
    private ButtonListener click = new ButtonListener();                    // Listener for Buttons
    private PasswordVerifier pwdVerifier = new PasswordVerifier();

    public static final String[] users = { "Cook", "Host", "Waiter", "Manager" };


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

        Utilities.updateFont(rButton = new JButton("Revenue"), .065);
        Utilities.updateFont(cButton = new JButton("User Controls"), .065);
        Utilities.updateFont(iButton = new JButton("Inventory"), .065);
        manButtons.add(rButton);
        manButtons.add(cButton);
        manButtons.add(iButton);
        control.add(manButtons);
        add(control, BorderLayout.CENTER);

        //*********************************************************************//
        // Inventory Frame
        //*********************************************************************//
        Utilities.startDayInfo(inventoryFrame, inventoryClock, "Welcome, Manager!", timeDateInvtry, .09, false);
        inventoryPanel.setLayout(new BorderLayout());
        JLabel orderStatus = new JLabel("Order Status:");
//        Utilities.updateFont(orderStatus, .025);
        Utilities.multiUpdateFont(.02, updateItm = new JButton("Update"), removeItm = new JButton("Remove"),
                checkAll = new JButton("Check All"), orderItem = new JButton("Order Item(s)"));
        Utilities.multiAdd(inventoryCtrls, removeItm, Box.createRigidArea(new Dimension(10, 0)),
                updateItm, Box.createRigidArea(new Dimension(10, 0)),
                checkAll, Box.createRigidArea(new Dimension(10, 0)), orderItem);

        //*********************************************************************//
        // Create, Populate, and Update Looks of Inventory Table
        //*********************************************************************//
        Object[][] inventoryData = {
                {"001", "Eggs", "20", "10", "PENDING", false},
                {"002", "Cheese", "05", "01", "ORDERED", false},
                {"003", "Bagels", "25", "03", "PENDING", false},
                {"004", "Bread", "25", "15", "CANCELED", false}
        };
        Object[] inventoryColumns = {"Order ID", "Item Description", "Quantity Needed",
                "Quantity In Stock","Status", "Select Item", "Order Quantity"};

        inventoryTable = new JTable() {
            @Override
            public Class<?> getColumnClass(int col) {
                if (col == 5) { return Boolean.class; }
                return super.getColumnClass(col);
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 5)
                    return true;
                if (col == 6)
                    return ((Boolean) getValueAt(row, 5)).booleanValue();
                else
                    return false;
            }
        };
        inventoryTable.setDefaultRenderer(Object.class, new TableRowRender());
        inventoryTable.setModel(new DefaultTableModel(inventoryData, inventoryColumns));

        inventoryPanel.add(inventoryCtrls, BorderLayout.NORTH);
        inventoryPanel.add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        inventoryFrame.add(inventoryPanel, BorderLayout.CENTER);

        //#####

        // Listeners for all the Buttons;
        cButton.addActionListener(click);
        iButton.addActionListener(click);
        checkAll.addActionListener(click);
        orderItem.addActionListener(click);

        /** Set Visible Last To Avoid Glitches/Flickering **/
        setVisible(true);                                                                 // Show on Screen
        setResizable(false);                                                              // Size is NOT adjustable (Always Maximized)
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
                if (!selectAll) { selectAll = true; checkAll.setText("Check None");}
                else { selectAll = false; checkAll.setText("Check All");}

                for (int line = 0; line < row; line++) {
                    inventoryTable.setValueAt(selectAll, line, 5);
                }
            }

            if (event.getSource() == orderItem) {
                int row = inventoryTable.getRowCount();
                boolean check;

                for (int line = 0; line < row; line++) {
                    check = (Boolean) inventoryTable.getValueAt(line, 5);

                    if (check) {
                        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                        Object value = model.getValueAt(line,6);
                        if (value != null) {
                            System.out.println(value.toString());
                        } else {
                            model.setValueAt(model.getValueAt(line, 2), line, 6);
                            System.out.println(model.getValueAt(line, 2).toString());
                        }
                        model.setValueAt("ORDERED", line, 4);
                    }
                }
            }

            if (event.getSource() == cButton) {
                if (pwdVerifier.verifyPwd("manager")) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, 1));
                    panel.add(new JLabel("Please choose a user:"));
                    JComboBox userList = new JComboBox(users);

                    JPasswordField oldPass = new JPasswordField();
                    oldPass.setDocument(new InputLimit(10));
                    oldPass.setBorder(BorderFactory.createTitledBorder("Enter Old Password (10 max):"));
                    JPasswordField newPassA = new JPasswordField();
                    newPassA.setDocument(new InputLimit(10));
                    newPassA.setBorder(BorderFactory.createTitledBorder("Enter New Password (10 max):"));
                    JPasswordField newPassB = new JPasswordField();
                    newPassB.setDocument(new InputLimit(10));
                    newPassB.setBorder(BorderFactory.createTitledBorder("Repeat New Password (10 max):"));

                    panel.add(userList);
                    panel.add(Box.createRigidArea(new Dimension(0,25)));
                    panel.add(oldPass);
                    panel.add(Box.createRigidArea(new Dimension(0,5)));
                    panel.add(newPassA);
                    panel.add(Box.createRigidArea(new Dimension(0,5)));
                    panel.add(newPassB);

                    JOptionPane.showConfirmDialog(null,
                            panel,
                            "Password Changer",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new ManagerDisplay();
    }
}
