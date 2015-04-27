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

        // Create Table Buttons (x11), Add ActionListener to them and Add them to Table Rows
        for (int i=1; i < 12; i++) {
            tableList[i] = new JButton("Table " + new DecimalFormat("00").format(i));           // Create button w/ Name
            Utilities.updateFont(tableList[i], .05);
            tableList[i].addActionListener(click);

            if (i < 5) { tableRowOne.add(tableList[i]);                                         // 1st Row: 1-4
            } else if (i < 8) { tableRowTwo.add(tableList[i]);                                  // 2nd Row: 5-7
            } else tableRowThree.add(tableList[i]);                                             // 3rd Row: 8-11
        }

        // Add Rows to Table Panel
        tables.setLayout(new GridLayout(0, 3, 0, 0));

        tables.add(tableRowOne);
        tables.add(tableRowTwo);
        tables.add(tableRowThree);
        add(tables, BorderLayout.CENTER);

        // Reserved Picture
        try {
            reserved = new ImageIcon(ImageIO.read(new URL("http://goo.gl/J2IJ6v")));
            occupied = new ImageIcon(ImageIO.read(new URL("http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Actions-dialog-ok-apply-icon.png")));
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

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
            for (int i=1; i<12; i++) {
                if (event.getSource() == tableList[i]) {
                    if (!clicked[i])
                    {
                        resPanel = new JPanel();
                        resPanel.setLayout(new BoxLayout(resPanel, 1));

                        // Arguments for OptionPane
                        JLabel text = new JLabel("Choose Activity", SwingConstants.CENTER);
                        String[] activities = {"Table Occupied", "Table Clear", "Make Reservation"};

                        // Activity OptionPane
                        int answer = JOptionPane.showOptionDialog(null, text, tableList[i].getText(),
                                0, JOptionPane.PLAIN_MESSAGE, null, activities, activities[0]);
                        if (answer == 0)
                        {
                            // Change to check mark for people seated at table
                            Utilities.updateFont(tableList[i], .03);
                            tableList[i].setIcon(occupied);
                            tableList[i].setForeground(Color.gray);
                        }
                        if (answer == 1)
                        {
                            // check that table has people currently sitting at it and not reserved
                            if (tableList[i].getIcon() == occupied)
                            {
                                // Set back to nothing for empty table
                                Utilities.updateFont(tableList[i], .05);
                                tableList[i].setIcon(null);
                                tableList[i].setForeground(Color.black);
                            }
                        }
                        if (answer == 2)
                        {
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
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }

                            Utilities.multiAdd(resPanel, resName, Box.createRigidArea(new Dimension(10, 0)),
                                    resDate, Box.createRigidArea(new Dimension(10, 0)),
                                    resTime, Box.createRigidArea(new Dimension(10, 0)),
                                    resPhone, Box.createRigidArea(new Dimension(10, 0)),
                                    resHead, Box.createRigidArea(new Dimension(10, 0)), resOrder);

                            // Cancel button to stop making reservation
                            final JButton cancelButton = new JButton("Cancel");
                            cancelButton.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    Window w = SwingUtilities.getWindowAncestor(cancelButton);

                                    if (w != null)
                                        w.setVisible(false);
                                }
                            });

                            // Okay button to confirm reservation and that all fields are filled
                            final JButton okayButton = new JButton("Okay");
                            final int index1 = i; 						// i need to be in the scope for the action listener
                            okayButton.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e)
                                {
                                    if (resName.getText().equals("") || resDate.getText().equals("") || resTime.getText().equals("")
                                            || resPhone.getText().equals("") || resHead.getText().equals(""))
                                    {
                                        JOptionPane.showMessageDialog(null, "Error: Must fill in all fields.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    else
                                    {
                                        resName.setEditable(false);
                                        resDate.setEditable(false);
                                        resTime.setEditable(false);
                                        resPhone.setEditable(false);
                                        resHead.setEditable(false);
                                        resOrder.setEditable(false);

                                        Utilities.updateFont(tableList[index1], .03);
                                        tableList[index1].setIcon(reserved);
                                        tableList[index1].setForeground(Color.gray);
                                        clicked[index1] = true;

                                        Window w = SwingUtilities.getWindowAncestor(okayButton);

                                        if (w != null)
                                            w.setVisible(false);
                                    }
                                }
                            });

                            // create dialog
                            JButton[] cancelOkayButtons = {okayButton, cancelButton};
                            JOptionPane.showOptionDialog(null, resPanel, tableList[i].getText(),
                                    0, JOptionPane.PLAIN_MESSAGE, null, cancelOkayButtons, cancelOkayButtons[1]);

                        } // if answer 2
                    } // if
                    else
                    {

                        // Open fields so you they can be edited/changed
                        JButton editBtn = new JButton("Edit");
                        editBtn.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                resName.setEditable(true);
                                resDate.setEditable(true);
                                resTime.setEditable(true);
                                resPhone.setEditable(true);
                                resHead.setEditable(true);
                                resOrder.setEditable(true);
                            }
                        });

                        // Button to save changes (more of a user button to keep from confusion)
                        final JButton saveBtn = new JButton("Save");
                        saveBtn.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                if (resName.getText().equals("") || resDate.getText().equals("") || resTime.getText().equals("")
                                        || resPhone.getText().equals("") || resHead.getText().equals(""))
                                {
                                    JOptionPane.showMessageDialog(null, "Error: Must fill in all fields.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    Window w = SwingUtilities.getWindowAncestor(saveBtn);

                                    if (w != null)
                                        w.setVisible(false);
                                }
                            }
                        });

                        // Button to cancel reservation and set table back to null
                        final JButton cancelBtn = new JButton("Cancel Reservation");
                        final int index2 = i; 						// i need to be in the scope for the action listener
                        cancelBtn.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                resName.setText("");
                                resDate.setText("");
                                resTime.setText("");
                                resPhone.setText("");
                                resHead.setText("");
                                resOrder.setText("");

                                Utilities.updateFont(tableList[index2], .05);
                                tableList[index2].setIcon(null);
                                tableList[index2].setForeground(Color.black);
                                clicked[index2] = false;

                                Window w = SwingUtilities.getWindowAncestor(cancelBtn);

                                if (w != null)
                                    w.setVisible(false);
                            }
                        });

                        // Button to close dialog
                        JButton closeBtn = new JButton("Close Dialog");
                        closeBtn.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e)
                            {
                                if (resName.getText().equals("") || resDate.getText().equals("") || resTime.getText().equals("")
                                        || resPhone.getText().equals("") || resHead.getText().equals(""))
                                {
                                    JOptionPane.showMessageDialog(null, "Error: Must fill in all fields.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    Window w = SwingUtilities.getWindowAncestor(cancelBtn);

                                    if (w != null)
                                        w.setVisible(false);
                                }
                            }
                        });

                        // Set buttons and create dialog
                        JButton[] options = {editBtn, saveBtn, cancelBtn, closeBtn};
                        JOptionPane.showOptionDialog(null, resPanel, "Showing Reservation Info for " + tableList[i].getText(),
                                0, JOptionPane.PLAIN_MESSAGE, null, options, options[3]);

                    } // Else
                } // Get Source
            } // Table Loop
        }
    } // ButtonListener

    public static void main(String[] args) throws Exception {
        new HostDisplay();
    }
}
