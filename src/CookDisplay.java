import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CookDisplay extends JFrame {
    private JPanel dayInfo = new JPanel(), onCall = new JPanel();                   // Top and Bottom Panels
    private JLabel timeDate = new JLabel(), tableMsg = new JLabel();                // Dynamic Time & Date; And Table Message

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel tableCtrls = new JPanel(), inventoryCtrls = new JPanel();        // Table's Button Control panel

    private JPanel inventory = new JPanel();
    private JTable orderTable, inventoryTable;
    private JButton resetOrd, startOrd, readyOrd, helpOrd;                          // Order Table Buttons
    private JButton addItm, updateItm, removeItm, clearItm;                         // Inventory Table Buttons
    private JFormattedTextField itemId, itemQty, itemName, itemStock, itemNotes;    // Inventory Table Inputs

    private ButtonListener click = new ButtonListener();
    private TableButtonHandler tblBtnHndlr = new TableButtonHandler();

    public CookDisplay() { setupCDisplay(); }

    private void setupCDisplay() {
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, "Welcome, Cook!", timeDate, .09, false);

        onCall.setLayout(new BorderLayout());
        inventory.setLayout(new BorderLayout());

        //*********************************************************************//
        // Initialize Control Inputs and Buttons for Order & Inventory Table
        //*********************************************************************//
        try {                                                                       // Due to MaskFormatter
            // Inventory Table - Set Format, Width, Alignment, and Border
            itemId = new JFormattedTextField(new MaskFormatter("###"));
            itemId.setColumns(5);
            itemId.setHorizontalAlignment(JTextField.CENTER);
            itemId.setBorder(BorderFactory.createTitledBorder("Item ID"));

            itemQty = new JFormattedTextField(new MaskFormatter("##"));
            itemQty.setColumns(5);
            itemQty.setHorizontalAlignment(JTextField.CENTER);
            itemQty.setBorder(BorderFactory.createTitledBorder("Quantity"));

            itemName = new JFormattedTextField();
            itemName.setColumns(25);
            itemName.setHorizontalAlignment(JTextField.CENTER);
            itemName.setBorder(BorderFactory.createTitledBorder("Name"));

            itemStock = new JFormattedTextField(new MaskFormatter("##"));
            itemStock.setColumns(5);
            itemStock.setHorizontalAlignment(JTextField.CENTER);
            itemStock.setBorder(BorderFactory.createTitledBorder("In Stock"));

            /**/
            itemNotes = new JFormattedTextField();
            itemNotes.setColumns(50);
            itemNotes.setHorizontalAlignment(JTextField.CENTER);
            itemNotes.setBorder(BorderFactory.createTitledBorder("Notes"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        JLabel orderStatus = new JLabel("Order Status:");
        Utilities.updateFont(orderStatus, .025);
        Utilities.multiUpdateFont(.02, resetOrd = new JButton("Reset"), startOrd = new JButton("Started"),
                readyOrd = new JButton("Ready"), helpOrd = new JButton("Help"));
        resetOrd.setBackground(Color.white);
        startOrd.setBackground(Color.yellow);
        readyOrd.setBackground(Color.green);
        helpOrd.setBackground(Color.red);

        Utilities.multiAdd(tableCtrls, orderStatus, Box.createRigidArea(new Dimension(10, 0)),
                resetOrd, Box.createRigidArea(new Dimension(10, 0)),
                startOrd, Box.createRigidArea(new Dimension(10, 0)),
                readyOrd, Box.createRigidArea(new Dimension(10, 0)), helpOrd);

        //*********************************************************************//
        // Create, Populate, and Update Looks of Order Table
        //*********************************************************************//
        Object[][] orderData = {
                {"001", "11", "Pizza, Juice, Fries, Soda", "11:10", "NEW"},
                {"002", "07", "Juice, Fries, Soda, Pizza", "11:30", "STARTED"},
                {"003", "09", "Fries, Soda, Pizza, Juice", "11:55", "READY"},
                {"004", "08", "Soda, Pizza, Juice, Fries", "12:15", "HELP"},
        };
        Object[] orderColumns = {"Order ID", "Table", "Order Detail", "Time Ordered", "Order Status"};
        orderTable = new JTable() {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        orderTable.setDefaultRenderer(Object.class, new TableRowRender());
        orderTable.setModel(new DefaultTableModel(orderData, orderColumns));

        onCall.add(tableCtrls, BorderLayout.NORTH);
        onCall.add(new JScrollPane(orderTable), BorderLayout.CENTER);                       // Add Scroll feature
        //*********************************************************************//

        Utilities.multiUpdateFont(.02, addItm = new JButton("Add"), updateItm = new JButton("Update"),
                removeItm = new JButton("Remove"), clearItm = new JButton("Clear Input"));
        Utilities.multiAdd(inventoryCtrls, itemId, itemQty, itemName, itemStock,
                Box.createRigidArea(new Dimension(10, 0)), addItm,
                Box.createRigidArea(new Dimension(10, 0)), updateItm,
                Box.createRigidArea(new Dimension(10, 0)), removeItm,
                Box.createRigidArea(new Dimension(10, 0)), clearItm);

        //*********************************************************************//
        // Create, Populate, and Update Looks of Inventory Table
        //*********************************************************************//
        Object[][] inventoryData = {
                {"001", "Eggs", "20", "10", "PENDING"},
                {"002", "Cheese", "05", "01", "ORDERED"},
                {"003", "Bagels", "25", "03", "PENDING"},
                {"004", "Bread", "25", "15", "CANCELED"}
        };
        Object[] inventoryColumns = {"Order ID", "Item Description", "Quantity Needed", "Quantity In Stock", "Status"};

        inventoryTable = new JTable() {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }

        };
        inventoryTable.setDefaultRenderer(Object.class, new TableRowRender());
        inventoryTable.setModel(new DefaultTableModel(inventoryData, inventoryColumns));

        inventory.add(inventoryCtrls, BorderLayout.NORTH);
        inventory.add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        //*********************************************************************//
        // Create, Populate, and Update Looks of Tabs in TabbedPane
        //*********************************************************************//
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 30));
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>OnCall</body></html>", onCall);
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>Inventory</body></html>", inventory);
        add(tabbedPane, BorderLayout.CENTER);

        Utilities.updateFont(tableMsg, .015);
        tableMsg.setForeground(Color.red);
        tableMsg.setHorizontalAlignment(JLabel.CENTER);
        add(tableMsg, BorderLayout.SOUTH);

        // Add action Listeners to buttons
        resetOrd.addActionListener(click);
        startOrd.addActionListener(click);
        readyOrd.addActionListener(click);
        helpOrd.addActionListener(click);

        addItm.addActionListener(click);
        updateItm.addActionListener(click);
        removeItm.addActionListener(click);
        clearItm.addActionListener(click);

        inventoryTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblBtnHndlr.setTableInput(inventoryTable, itemId, itemName, itemQty, itemStock);
            }
        });

        /** Set Visible Last To Avoid Glitches/Flickering **/
        setVisible(true);
        setResizable(false);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //*********************************************************************//
            // Order Table
            //*********************************************************************//
            if (event.getSource() == resetOrd) { tblBtnHndlr.resetOrder(orderTable, tableMsg); }
            if (event.getSource() == startOrd) { tblBtnHndlr.startOrder(orderTable, tableMsg); }
            if (event.getSource() == readyOrd) { tblBtnHndlr.readyOrder(orderTable, tableMsg); }
            if (event.getSource() == helpOrd) { tblBtnHndlr.helpOrder(orderTable, tableMsg); }

            //*********************************************************************//
            // Inventory Table
            //*********************************************************************//
            if (event.getSource() == clearItm) {
                tblBtnHndlr.clearTableInput(inventoryTable, tableMsg, itemId, itemName, itemQty, itemStock);
            }
            if (event.getSource() == addItm) {
                tableMsg.setText("");
                if (!tblBtnHndlr.isInputEmpty(tableMsg, itemId, itemName, itemQty, itemStock)) {
                    tblBtnHndlr.addRow(inventoryTable, new JLabel("inventoryTable"), itemId, itemName, itemQty, itemStock);
                    tblBtnHndlr.clearTableInput(inventoryTable, tableMsg, itemId, itemName, itemQty, itemStock);
                }
            }
            if (event.getSource() == updateItm) {
                tableMsg.setText("");
                if (!tblBtnHndlr.isInputEmpty(tableMsg, itemId, itemName, itemQty, itemStock)
                        && !tblBtnHndlr.isTableEmpty(inventoryTable, tableMsg)) {
                    tblBtnHndlr.updateRow(inventoryTable, tableMsg, itemId, itemName, itemQty, itemStock);
                    tblBtnHndlr.clearTableInput(inventoryTable, tableMsg, itemId, itemName, itemQty, itemStock);
                }
            }
            if (event.getSource() == removeItm) {
                if (!tblBtnHndlr.isTableEmpty(inventoryTable, tableMsg)) {
                    tblBtnHndlr.removeRow(inventoryTable, tableMsg);
                    tblBtnHndlr.clearTableInput(inventoryTable, tableMsg, itemId, itemName, itemQty, itemStock);
                }
            }
        }
    }

    public static void main(String[] args) {
        new CookDisplay();
    }
}
