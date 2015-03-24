import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.text.AbstractDocument;

public class HostDisplay extends JFrame {
    private JPanel dayInfo = new JPanel(), tables = new JPanel();           // Top (dayInfo), Bottom (tables) sections
    private JButton[] tableList = new JButton[12];
    private boolean[] clicked = new boolean[12];

    private ImageIcon reserved;

    private JLabel timeDate = new JLabel();                                 // Dynamic Time & Date;
    private ButtonListener click = new ButtonListener();                    // Listener for Buttons

    public HostDisplay() {
        setupHDisplay();
    }

    private void setupHDisplay() {
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, "Welcome, Hoster!", timeDate, .09, false);

        // Create + Set Table Row(s) Layout
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
            tableList[i].addActionListener(click);                                              // Add ActionListener

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
            reserved = new ImageIcon(ImageIO.read(new URL("http://icons.iconarchive.com/icons/blackvariant/button-ui-system-apps/128/X11-icon.png")));
        } catch(MalformedURLException mue) {
            mue.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        /** Set Visible Last To Avoid Glitches/Flickering **/
        setVisible(true);                                                                       // Show on Screen
        setResizable(false);                                                                    // Size is NOT adjustable (Always Maximized)
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener {
        JPanel panel;
        JTextField resName, resOrder;
        JFormattedTextField resDate, resTime, resPhone, resHead;

        public void actionPerformed(ActionEvent event) {
            for (int i=1; i<12; i++) {
                if (event.getSource() == tableList[i]) {
                    if (!clicked[i]) {
                        panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, 1));

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

                        panel.add(resName);
                        panel.add(Box.createRigidArea(new Dimension(0, 5)));
                        panel.add(resDate);
                        panel.add(Box.createRigidArea(new Dimension(0,5)));
                        panel.add(resTime);
                        panel.add(Box.createRigidArea(new Dimension(0, 5)));
                        panel.add(resPhone);
                        panel.add(Box.createRigidArea(new Dimension(0,5)));
                        panel.add(resHead);
                        panel.add(Box.createRigidArea(new Dimension(0,5)));
                        panel.add(resOrder);

                        int response = JOptionPane.showConfirmDialog(null,
                                panel,
                                "Reservation Info for " + tableList[i].getText(),
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

                        if (response == 0) {                                                // OK
                            Utilities.updateFont(tableList[i], .03);
                            tableList[i].setIcon(reserved);
                            tableList[i].setForeground(Color.gray);
                            clicked[i] = true;
                        }

//                        System.out.println("N: " + resName.getText());
//                        System.out.println("D: " + resDate.getValue());
//                        System.out.println("T: " + resTime.getValue());
//                        System.out.println("#: " + resPhone.getValue());
//                        System.out.println("H: " + resHead.getValue());
                    } else {
                        resName.setEditable(false);
                        resDate.setEditable(false);
                        resTime.setEditable(false);
                        resPhone.setEditable(false);
                        resHead.setEditable(false);
                        resOrder.setEditable(false);

//                        System.out.println(resName.getText());
//                        System.out.println(Utilities.validateName(resName.getText()));
                        
                        
                        String[] options = {"Edit", "Save", "Cancel Reservation", "Close Dialog"};
                        int response = JOptionPane.showOptionDialog(null, panel, "Showing Reservation Info for " + tableList[i].getText(),
                                0, JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
                      	
                        if (response == 0) {                                             // EDIT
                            resName.setEditable(true);
                            resDate.setEditable(true);
                            resTime.setEditable(true);
                            resPhone.setEditable(true);
                            resHead.setEditable(true);
                            resOrder.setEditable(true);
                            response = JOptionPane.showOptionDialog(null, panel, "Showing Reservation Info for " + tableList[i].getText(),
                                    0, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
                        }
                        

                        if (response == 2) {                                                // CANCEL
                            resName.setText("");
                            resDate.setText("");
                            resTime.setText("");
                            resPhone.setText("");
                            resHead.setText("");
                            resOrder.setText("");

                            Utilities.updateFont(tableList[i], .05);
                            tableList[i].setIcon(null);
                            tableList[i].setForeground(Color.black);
                            clicked[i] = false;
                           
                        }
                        
                    } // Else
                } // Get Source
            } // Table Loop
        }
    } // ButtonListener

    public static void main(String[] args) throws Exception {
        new HostDisplay();
    }
}
