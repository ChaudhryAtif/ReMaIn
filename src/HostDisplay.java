import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class HostDisplay extends JFrame {
    private JPanel dayInfo = new JPanel(), tables = new JPanel();           // Top (dayInfo), Bottom (tables) sections
    private JButton[] tableList = new JButton[12];
    private boolean[] clicked = new boolean[12];

    private ImageIcon reserved;
    private ImageIcon occupied;

    private JLabel timeDate = new JLabel();                                 // timeDate: Dynamic Time & Date;
    private ButtonListener click = new ButtonListener();

//    private String[] table1Data = {"", "", "", "", "", "", "", ""};
//    private String[] table2Data = {"", "", "", "", "", "", "", ""};
//    private String[] table3Data = {"", "", "", "", "", "", "", ""};
//    private String[] table4Data = {"", "", "", "", "", "", "", ""};
//    private String[] table5Data = {"", "", "", "", "", "", "", ""};
//    private String[] table6Data = {"", "", "", "", "", "", "", ""};
//    private String[] table7Data = {"", "", "", "", "", "", "", ""};
//    private String[] table8Data = {"", "", "", "", "", "", "", ""};
//    private String[] table9Data = {"", "", "", "", "", "", "", ""};
//    private String[] table10Data = {"", "", "", "", "", "", "", ""};
//    private String[] table11Data = {"", "", "", "", "", "", "", ""};

    // Populated by Connie
    /*private String[] table1Data = {"1","Reserved","Connor", "12/12/1212", "12:03 am", "(222) 222-2222", "2", "nothing"};
    private String[] table2Data = {"2","Occupied","", "", "", "", "", ""};
    private String[] table3Data = {"3","Reserved","Jordan", "13/13/1313", "13:03 am", "(333) 333-3333", "3", "Brennan is ehh"};
    private String[] table4Data = {"4","Reserved","Max", "14/14/1414", "14:03 am", "(444) 444-4444", "4", "Databases are cool"};
    private String[] table5Data = {"5", "","", "", "", "", "", ""};
    private String[] table6Data = {"6","Reserved","Atif", "15/15/1515", "15:03 am", "(555) 555-5555", "5", "Incoherent babble"};
    private String[] table7Data = {"7","Reserved","Brennan", "16/16/1616", "16:03 am", "(666) 666-6666", "6", "Jordo is better"};
    private String[] table8Data = {"8","Reserved","Scannell", "17/17/1717", "17:03 am", "(777) 777-7777", "7", "Languages"};
    private String[] table9Data = {"9","Reserved","Sukhodolsky", "18/18/1818", "18:03 am", "(888) 888-8888", "8", "For Russia"};
    private String[] table10Data = {"10","Reserved","Chambers", "19/19/1919", "19:03 am", "(999) 999-9999", "9", "Data Structures"};
    private String[] table11Data = {"11","Reserved","Goldwasser", "11/11/1111", "11:03 am", "(111) 111-1111", "1", "ALGORITHMS"};
    private String[][] reservationData = {table1Data, table2Data, table3Data, table4Data, table5Data, table6Data,
            table7Data, table8Data, table9Data, table10Data, table11Data};
*/
//    private String[][] reservationData = new String[11][8];
    private String[][] reservationData = TableManager.getTables();

    public String[][] getResData() {
        return reservationData;
    }

    public void setResData(String[]... tableData) {
        int table = 1; //$
        for (String[] resInfo : tableData) {
            reservationData[table] = resInfo;
            table++;
        }
    }

    public HostDisplay() {
        setupHDisplay();
    }

    private void setupHDisplay() {
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, "Welcome, Hoster!", timeDate, .09, false);

        JPanel tableRowOne = new JPanel();
        JPanel tableRowTwo = new JPanel();
        JPanel tableRowThree = new JPanel();
        tableRowOne.setLayout(new GridLayout(4, 0, 0, 0));
        tableRowTwo.setLayout(new GridLayout(3, 0, 0, 0));
        tableRowThree.setLayout(new GridLayout(4, 0, 0, 0));

        // Reserved Picture
        try {
            reserved = new ImageIcon(ImageIO.read(new URL("http://goo.gl/J2IJ6v")));
            occupied = new ImageIcon(ImageIO.read(new URL("http://goo.gl/U6L1lv")));
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }


        // Create Table Buttons (x11), Add ActionListener to them and Add them to Table Rows
        for (int tableNo=1; tableNo < 12; tableNo++) {
            tableList[tableNo] = new JButton("Table " + new DecimalFormat("00").format(tableNo));           // Create button w/ Name
            Utilities.updateFont(tableList[tableNo], .05);
            tableList[tableNo].addActionListener(click);

            if (tableNo < 5) { tableRowOne.add(tableList[tableNo]);                                         // 1st Row: 1-4
            } else if (tableNo < 8) { tableRowTwo.add(tableList[tableNo]);                                  // 2nd Row: 5-7
            } else tableRowThree.add(tableList[tableNo]);                                             // 3rd Row: 8-11

            // Get table status
            String[] tableData = reservationData[tableNo]; //$
            if (tableData[1].equals("Reserved")) {
                // Change to x mark for reserved table
                Utilities.updateFont(tableList[tableNo], .03);
                tableList[tableNo].setIcon(reserved);
                tableList[tableNo].setForeground(Color.gray);
            }

            else if (tableData[1].equals("Occupied")) {
                // Change to check mark for people seated at table
                Utilities.updateFont(tableList[tableNo], .03);
                tableList[tableNo].setIcon(occupied);
                tableList[tableNo].setForeground(Color.gray);
            }
        }

        // Add Rows to Table Panel
        tables.setLayout(new GridLayout(0, 3, 0, 0));

        tables.add(tableRowOne);
        tables.add(tableRowTwo);
        tables.add(tableRowThree);
        add(tables, BorderLayout.CENTER);

        /** Set Visible Last To Avoid Glitches/Flickering **/
        setVisible(true);
        setResizable(false);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener {
        JPanel resPanel;
        JTextField resName, resOrder;
        JFormattedTextField resDate, resTime, resPhone, resHead;

        public void actionPerformed(ActionEvent event) {
            for (int tableNo=1; tableNo<12; tableNo++) {
                resPanel = new JPanel();
                resPanel.setLayout(new BoxLayout(resPanel, 1));

                try {                                                                   // Due to MaskFormatter
                    resName = new JTextField();
                    resName.setDocument(new InputLimit(20));
                    resName.setBorder(BorderFactory.createTitledBorder("Name"));
                    ((AbstractDocument) resName.getDocument()).setDocumentFilter(new LetterDocumentFilter()); // filters type contents

                    resDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
                    resDate.setToolTipText("Date format:mm/dd/yyyy");
                    resDate.setBorder(BorderFactory.createTitledBorder("Date"));

                    resTime = new JFormattedTextField(new MaskFormatter("##:## ??"));
                    resTime.setToolTipText("Time Format: hh:mm am/pm");
                    resTime.setBorder(BorderFactory.createTitledBorder("Time"));

                    resPhone = new JFormattedTextField(new MaskFormatter("(###) ###-####"));
                    resPhone.setBorder(BorderFactory.createTitledBorder("Phone Number"));

                    resHead = new JFormattedTextField(new MaskFormatter("#"));
                    resHead.setBorder(BorderFactory.createTitledBorder("Number of Heads"));

                    resOrder = new JTextField();
                    resOrder.setDocument(new InputLimit(30));
                    resOrder.setBorder(BorderFactory.createTitledBorder("Order/Comments/Special Order"));
                }
                catch (java.text.ParseException e) {e.printStackTrace();}

                Utilities.multiAdd(resPanel, resName, Box.createRigidArea(new Dimension(10, 0)),
                        resDate, Box.createRigidArea(new Dimension(10, 0)),
                        resTime, Box.createRigidArea(new Dimension(10, 0)),
                        resPhone, Box.createRigidArea(new Dimension(10, 0)),
                        resHead, Box.createRigidArea(new Dimension(10, 0)), resOrder);

                final String[] tableData = reservationData[tableNo];//$

                // Check if table is reserved or not
                if (tableData[1].equals("Reserved"))
                    clicked[tableNo] = true;

                if (event.getSource() == tableList[tableNo]) {
                    if (!clicked[tableNo]) {
                        // Arguments for OptionPane
                        JLabel text = new JLabel("Choose Activity", SwingConstants.CENTER);
                        String[] activities;
                        if (tableList[tableNo].getIcon() == occupied) {
                            activities =  new String[] {"Clear Table", "Make Reservation"};
                        } else {
                            activities = new String[] {"Occupy Table", "Make Reservation"};
                        }

                        // Activity OptionPane
                        int answer = JOptionPane.showOptionDialog(null, text, tableList[tableNo].getText(),
                                0, JOptionPane.PLAIN_MESSAGE, null, activities, activities[0]);
                        if (answer == 0) {
                            if (activities[0].equals("Occupy Table")) {
                                tableData[1] = "Occupied";

                                // Change to check mark for people seated at table
                                Utilities.updateFont(tableList[tableNo], .03);
                                tableList[tableNo].setIcon(occupied);
                                tableList[tableNo].setForeground(Color.gray);
                                
                                // Update the core code
                                TableManager.setStatus(tableNo, "Occupied");
                            }
                            else if (activities[0].equals("Clear Table")) {
                                // Set back to nothing for empty table
                                Utilities.updateFont(tableList[tableNo], .05);
                                tableList[tableNo].setIcon(null);
                                tableList[tableNo].setForeground(Color.black);
                                
                                // Update the core code
                                TableManager.setStatus(tableNo, "Cleared");
                            }
                        }
                        if (answer == 1) {
                            // Cancel button to stop making reservation
                            final JButton cancelButton = new JButton("Cancel");
                            cancelButton.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    Window win = SwingUtilities.getWindowAncestor(cancelButton);

                                    if (win != null)
                                        win.setVisible(false);
                                }
                            });

                            // Okay button to confirm reservation and that all fields are filled
                            final JButton reserveButton = new JButton("Reserve");
                            final int index = tableNo; 						// it needs to be in the scope for the action listener
                            reserveButton.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    if (resName.getText().trim().equals("") || resDate.getText().equals("  /  /    ") || resTime.getText().equals("  :     ")
                                            || resPhone.getText().equals("(   )    -    ") || resHead.getText().equals(" ")) {
                                        JOptionPane.showMessageDialog(null, "Error: Must fill in all fields.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        // Cannot edit fields until button clicked
                                        resName.setEditable(false);
                                        resDate.setEditable(false);
                                        resTime.setEditable(false);
                                        resPhone.setEditable(false);
                                        resHead.setEditable(false);
                                        resOrder.setEditable(false);
                                        // save all values to array for table
                                        tableData[0] = String.valueOf(index);
                                        tableData[1] = "Reserved";
                                        tableData[2] = resName.getText();
                                        tableData[3] = resDate.getText();
                                        tableData[4] = resTime.getText();
                                        tableData[5] = resPhone.getText();
                                        tableData[6] = resHead.getText();
                                        tableData[7] = resOrder.getText();

                                        Utilities.updateFont(tableList[index], .03);
                                        tableList[index].setIcon(reserved);
                                        tableList[index].setForeground(Color.gray);
                                        clicked[index] = true;
                                        
                                        // Update the core code
                                        TableManager.setStatus(index, "Reserved");
                                        TableManager.setReservationInfo(new Integer(tableData[0]), tableData[2],
                                        		tableData[3], tableData[4], tableData[5], tableData[6], tableData[7]);

                                        Window w = SwingUtilities.getWindowAncestor(reserveButton);

                                        if (w != null)
                                            w.setVisible(false);
                                    } // Else
                                } // actionPerformed
                            }); // addActionListener

                            // create dialog
                            JButton[] cancelOkayButtons = {reserveButton, cancelButton};
                            JOptionPane.showOptionDialog(null, resPanel, tableList[tableNo].getText(),
                                    0, JOptionPane.PLAIN_MESSAGE, null, cancelOkayButtons, cancelOkayButtons[0]);

                        } // if answer 2
                    } else {
                        // Set uneditable since it isn't done before this
                        resName.setEditable(false);
                        resDate.setEditable(false);
                        resTime.setEditable(false);
                        resPhone.setEditable(false);
                        resHead.setEditable(false);
                        resOrder.setEditable(false);
                        // Populate fields from saved Arrays
                        resName.setText(tableData[2]);
                        resDate.setText(tableData[3]);
                        resTime.setText(tableData[4]);
                        resPhone.setText(tableData[5]);
                        resHead.setText(tableData[6]);
                        resOrder.setText(tableData[7]);

                        final int index = tableNo; // it needs to be in the scope for the action listener

                        // Button to save changes (more of a user button to keep from confusion)
                        final JButton doneBtn = new JButton("Done");
                        doneBtn.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                if (resName.getText().trim().equals("") || resDate.getText().equals("  /  /    ") || resTime.getText().equals("  :     ")
                                        || resPhone.getText().equals("(   )    -    ") || resHead.getText().equals(" ")) {
                                    JOptionPane.showMessageDialog(null, "Error: Must fill in all fields.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    resName.setEditable(false);
                                    resDate.setEditable(false);
                                    resTime.setEditable(false);
                                    resPhone.setEditable(false);
                                    resHead.setEditable(false);
                                    resOrder.setEditable(false);

                                    tableData[0] = String.valueOf(index);
                                    tableData[1] = "Reserved";
                                    tableData[2] = resName.getText();
                                    tableData[3] = resDate.getText();
                                    tableData[4] = resTime.getText();
                                    tableData[5] = resPhone.getText();
                                    tableData[6] = resHead.getText();
                                    tableData[7] = resOrder.getText();
                                    
                                    // Update the core code
                                    TableManager.setStatus(index, "Reserved");
                                    TableManager.setReservationInfo(new Integer(tableData[0]), tableData[2],
                                    		tableData[3], tableData[4], tableData[5], tableData[6], tableData[7]);

                                    Window w = SwingUtilities.getWindowAncestor(doneBtn);

                                    if (w != null)
                                        w.setVisible(false);
                                }
                            }
                        });

                        // Open fields so you they can be edited/changed
                        JButton editBtn = new JButton("Edit");
                        editBtn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                resName.setEditable(true);
                                resDate.setEditable(true);
                                resTime.setEditable(true);
                                resPhone.setEditable(true);
                                resHead.setEditable(true);
                                resOrder.setEditable(true);
                            }
                        });

                        // Button to cancel reservation and set table back to null
                        final JButton cancelBtn = new JButton("Cancel Reservation");
                        cancelBtn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Set Array back to empty values
                                tableData[1] = "";
                                tableData[2] = "";
                                tableData[3] = "";
                                tableData[4] = "";
                                tableData[5] = "";
                                tableData[6] = "";
                                tableData[7] = "";

                                Utilities.updateFont(tableList[index], .05);
                                tableList[index].setIcon(null);
                                tableList[index].setForeground(Color.black);
                                clicked[index] = false;
                                
                                TableManager.setStatus(index, "Cleared");

                                Window win = SwingUtilities.getWindowAncestor(cancelBtn);

                                if (win != null)
                                    win.setVisible(false);
                            }
                        });

                        // Set buttons and create dialog
                        JButton[] options = {doneBtn, editBtn, cancelBtn};
                        JOptionPane.showOptionDialog(null, resPanel, "Showing Reservation Info for " + tableList[tableNo].getText(),
                                0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    } // Else
                } // Get Source
            } // Table Loop
        } // ButtonListener
    }

    public static void main(String[] args) throws Exception {
        new HostDisplay();
    }
}
