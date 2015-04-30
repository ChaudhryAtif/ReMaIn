import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class WaiterDisplay extends JFrame {
    private JPanel dayInfo = new JPanel();                                          // Top Panel
    private JLabel timeDate = new JLabel();                                         // Dynamic Time & Date;
    private JTabbedPane tabbedPane = new JTabbedPane();

    // Tabs
    private JPanel manageOrders = new JPanel();
    private JPanel foodStatus = new JPanel();
    private JPanel tablesView = new JPanel();

    // Control Panels; and Tables
    private JPanel orderCtrls = new JPanel(), foodCtrls = new JPanel();
    private JTable orderTable, foodTable;

    private TableButtonHandler tblBtnHndlr = new TableButtonHandler();

    // Order Table Controls
    private JLabel tableMsg = new JLabel("");
    private JFormattedTextField orderId, orderTableNo, orderInfo, orderTime, orderNotes;
    private JButton addOrd, updateOrd, removeOrd, clearOrd;

    // Food Table Controls
    private JFormattedTextField foodTableNo, foodHeadServing, foodNotes;
    private JButton addFood, updateFood, removeFood, clearFood;

    /** Table ComboBox? **/
    private JComboBox waiterCBox = new JComboBox();
    private JComboBox statusCBox = new JComboBox();

    private ButtonListener click = new ButtonListener();

    private JPanel tables = new JPanel();
    private JButton[] tableList = new JButton[12];

    public WaiterDisplay() {
        setupWDisplay();
    }

    private void setupWDisplay() {
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, "Welcome, Waiter!", timeDate, .09, false);

        manageOrders.setLayout(new BorderLayout());
        
        //*********************************************************************//
        // Initialize Control Inputs and Buttons for Order & Food Table
        //*********************************************************************//
        try {                                                                       // Due to MaskFormatter
            NumberFormat numFormat = NumberFormat.getNumberInstance();
            numFormat.setMinimumIntegerDigits(1);
            numFormat.setMaximumIntegerDigits(3);
            numFormat.setMaximumFractionDigits(0);

            orderId = new JFormattedTextField(numFormat);
            orderId.setColumns(5);
            orderId.setHorizontalAlignment(JTextField.CENTER);
            orderId.setBorder(BorderFactory.createTitledBorder("Order ID"));

            orderTableNo = new JFormattedTextField(new MaskFormatter("##"));
            orderTableNo.setColumns(5);
            orderTableNo.setHorizontalAlignment(JTextField.CENTER);
            orderTableNo.setBorder(BorderFactory.createTitledBorder("Table"));

            foodTableNo = new JFormattedTextField(new MaskFormatter("##"));
            foodTableNo.setColumns(5);
            foodTableNo.setHorizontalAlignment(JTextField.CENTER);
            foodTableNo.setBorder(BorderFactory.createTitledBorder("Table"));

            foodHeadServing = new JFormattedTextField(new MaskFormatter("#"));
            foodHeadServing.setColumns(10);
            foodHeadServing.setHorizontalAlignment(JTextField.CENTER);
            foodHeadServing.setBorder(BorderFactory.createTitledBorder("Heads Serving"));

            orderInfo = new JFormattedTextField();
            orderInfo.setColumns(25);
            orderInfo.setHorizontalAlignment(JTextField.CENTER);
            orderInfo.setBorder(BorderFactory.createTitledBorder("Order"));

            orderTime = new JFormattedTextField(new MaskFormatter("##:##"));
            orderTime.setColumns(10);
            orderTime.setHorizontalAlignment(JTextField.CENTER);
            orderTime.setBorder(BorderFactory.createTitledBorder("Time Ordered"));

            orderNotes = new JFormattedTextField();
            orderNotes.setColumns(25);
            orderNotes.setHorizontalAlignment(JTextField.CENTER);
            orderNotes.setBorder(BorderFactory.createTitledBorder("Order Notes"));

            foodNotes = new JFormattedTextField();
            foodNotes.setColumns(25);
            foodNotes.setHorizontalAlignment(JTextField.CENTER);
            foodNotes.setBorder(BorderFactory.createTitledBorder("Food Notes"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        Utilities.multiUpdateFont(.02, addOrd = new JButton("Add"), updateOrd = new JButton("Update"),
                removeOrd = new JButton("Remove"), clearOrd = new JButton("Clear Input"));
        Utilities.multiAdd(orderCtrls, orderId, orderTableNo, orderInfo, orderTime, orderNotes,
                Box.createRigidArea(new Dimension(10, 0)), addOrd,
                Box.createRigidArea(new Dimension(10, 0)), updateOrd,
                Box.createRigidArea(new Dimension(10, 0)), removeOrd,
                Box.createRigidArea(new Dimension(10, 0)), clearOrd);


        //*********************************************************************//
        // Create, Populate, and Update Looks of Order Table
        //*********************************************************************//
		Object[][] orderData = OrderManager.getOrders();
        Object[] orderColumns = {"Order ID", "Table", "Order Detail", "Time Ordered", "Notes", "Order Status"};
        orderTable = new JTable() {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        orderTable.setDefaultRenderer(Object.class, new TableRowRender());
        orderTable.setModel(new DefaultTableModel(orderData, orderColumns));

        manageOrders.add(orderCtrls, BorderLayout.NORTH);
        manageOrders.add(new JScrollPane(orderTable), BorderLayout.CENTER);
        //*********************************************************************//

        foodStatus.setLayout(new BorderLayout());
        waiterCBox.setModel(new DefaultComboBoxModel(new String[]{"Max", "Connor", "Connie", "Maxie"}));
        statusCBox.setModel(new DefaultComboBoxModel(new String[]{"Appetizer", "Soup", "Dessert"}));


        Utilities.multiUpdateFont(.02, addFood = new JButton("Add"), updateFood = new JButton("Update"),
                removeFood = new JButton("Remove"), clearFood = new JButton("Clear Input"));
        Utilities.multiAdd(foodCtrls, waiterCBox, foodTableNo, foodHeadServing, foodNotes, statusCBox,
                Box.createRigidArea(new Dimension(10, 0)), addFood,
                Box.createRigidArea(new Dimension(10, 0)), updateFood,
                Box.createRigidArea(new Dimension(10, 0)), removeFood,
                Box.createRigidArea(new Dimension(10, 0)), clearFood
        );

        //*********************************************************************//
        // Create, Populate, and Update Looks of Food Table
        //*********************************************************************//
        Object[][] foodData = {
                {"Max", "11", "3", "Test", "Dessert"},
                {"Connie", "02", "5", "", "Appetizer"},
                {"Maxie", "05", "2", "Working", "Soup"}
        };
        Object[] foodColumns = {"Waiter", "Table", "Heads Serving", "Notes", "Food Status"};


        foodTable = new JTable() {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        foodTable.setDefaultRenderer(Object.class, new TableRowRender());
        foodTable.setModel(new DefaultTableModel(foodData, foodColumns));

        foodStatus.add(foodCtrls, BorderLayout.NORTH);
        foodStatus.add(new JScrollPane(foodTable), BorderLayout.CENTER);
		
        //*********************************************************************//
        //*********************************************************************//

        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 25));
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>Manage Orders</body></html>", manageOrders);
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>Food Status</body></html>", foodStatus);
        add(tabbedPane, BorderLayout.CENTER);

        Utilities.updateFont(tableMsg, .015);
        tableMsg.setForeground(Color.red);
        tableMsg.setHorizontalAlignment(JLabel.CENTER);
        add(tableMsg, BorderLayout.SOUTH);

        // Add action Listeners to buttons
        addOrd.addActionListener(click);
        updateOrd.addActionListener(click);
        removeOrd.addActionListener(click);
        clearOrd.addActionListener(click);

        addFood.addActionListener(click);
        updateFood.addActionListener(click);
        removeFood.addActionListener(click);
        clearFood.addActionListener(click);

        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblBtnHndlr.setTableInput(orderTable, orderId, orderTableNo, orderInfo, orderTime);}
        });

        foodTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) { foodTableMouseClicked(evt); }
        });

        /** Set These Last To Avoid Glitches/Flickering **/
        setVisible(true);
        setResizable(false);                                                              // (Always Maximized)
    }

    private void foodTableMouseClicked(MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) foodTable.getModel();
        waiterCBox.setSelectedItem(model.getValueAt(foodTable.getSelectedRow(), 0).toString());
        foodTableNo.setText(model.getValueAt(foodTable.getSelectedRow(), 1).toString());
        foodHeadServing.setText(model.getValueAt(foodTable.getSelectedRow(), 2).toString());
        foodNotes.setText(model.getValueAt(foodTable.getSelectedRow(), 3).toString());
        statusCBox.setSelectedItem(model.getValueAt(foodTable.getSelectedRow(), 4).toString());
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFormattedTextField[] jInputFields = {orderId, orderTableNo, orderInfo, orderTime, orderNotes};
            //*********************************************************************//
            // Order Table
            //*********************************************************************//
            if (event.getSource() == addOrd) {
                tableMsg.setText("");
                if (!tblBtnHndlr.isInputEmpty(tableMsg, orderId, orderTableNo, orderInfo, orderTime)) {
                    tblBtnHndlr.addRow(orderTable, new JLabel("orderTable"), jInputFields);
                    tblBtnHndlr.clearTableInput(orderTable, tableMsg, jInputFields);
                }
            }
            if (event.getSource() == updateOrd) {
                tableMsg.setText("");
                if (!tblBtnHndlr.isInputEmpty(tableMsg, orderId, orderTableNo, orderInfo, orderTime)) {
                    tblBtnHndlr.updateRow(orderTable, tableMsg, new JLabel("orderTable"), jInputFields);
                    tblBtnHndlr.clearTableInput(orderTable, tableMsg, jInputFields);
                }
            }
            if (event.getSource() == removeOrd) {
                if (!tblBtnHndlr.isTableEmpty(orderTable, tableMsg)) {
                    tblBtnHndlr.removeRow(orderTable, tableMsg, new JLabel("orderTable"));
                    tblBtnHndlr.clearTableInput(orderTable, tableMsg, jInputFields);
                }
            }
            if (event.getSource() == clearOrd) {
                tblBtnHndlr.clearTableInput(orderTable, tableMsg, jInputFields);
            }

            //*********************************************************************//
            // Food Table
            //*********************************************************************//
            if (event.getSource() == addFood) {
                tableMsg.setText("");
                if (!tblBtnHndlr.isInputEmpty(tableMsg, foodTableNo, foodHeadServing)) {
                    tblBtnHndlr.addFood(foodTable, tableMsg, waiterCBox, statusCBox, foodTableNo, foodHeadServing, foodNotes);
                }
            }
            if (event.getSource() == updateFood) {
                tableMsg.setText("");
                if (!tblBtnHndlr.isInputEmpty(tableMsg, foodTableNo, foodHeadServing)) {
                    tblBtnHndlr.updateFood(foodTable, tableMsg, waiterCBox, statusCBox, foodTableNo, foodHeadServing, foodNotes);
                }

            }
            if (event.getSource() == removeFood) {
                if (!tblBtnHndlr.isTableEmpty(foodTable, tableMsg)) {
                    tblBtnHndlr.removeRow(foodTable, tableMsg, new JLabel("foodTable"));
                    tblBtnHndlr.clearFood(foodTable, tableMsg, waiterCBox, statusCBox, foodTableNo, foodHeadServing, foodNotes);
                }
            }
            if (event.getSource() == clearFood) {
                tblBtnHndlr.clearFood(foodTable, tableMsg, waiterCBox, statusCBox, foodTableNo, foodHeadServing, foodNotes);
            }
        }
    }

    public static void main(String[] args) {
        new WaiterDisplay();
    }
}
