import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDisplay extends JFrame {
    JPanel dayInfo = new JPanel();                                          // Top Section
    JPanel users = new JPanel(new GridLayout(2, 2));                        // Bottom Section
    JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;

    private JButton cook, host, waiter, manager;
    ButtonListener click = new ButtonListener();                            // Listener for Buttons

    PasswordVerifier pwdVerifier = new PasswordVerifier();                  // Initialize PasswordVerifier Class

    public LoginDisplay() {
        // Set Window/Frame's Characteristics
//        super("Restaurant Management Interface");                           // Window Title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                     // Close on Exit

        setUndecorated(true);                                               //\
        Color color = UIManager.getColor("activeCaptionBorder");            // Removes Title Bar (Disable Drag)
        getRootPane().setBorder(BorderFactory.createLineBorder(color, 4));  ///

        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLayout(new GridLayout(2, 1));                                    // Set Layout
        setLocationRelativeTo(null);                                        // Position @ Center
        setVisible(true);                                                   // Show on Screen
        setResizable(false);                                                // Size is NOT adjustable (Always Maximized)

        Utilities.startClock(timeAndDate);                                  // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .1);                              // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);                                           // Add Time and Date to GUI

        // Create buttons for users, and update font
        cook = new JButton("Cook");
        Utilities.updateFont(cook, .1);
        host = new JButton("Host");
        Utilities.updateFont(host, .1);
        waiter = new JButton("Waiter");
        Utilities.updateFont(waiter, .1);
        manager = new JButton("Manager");
        Utilities.updateFont(manager, .1);

        // Add users to the JPanel
        users.add(cook);
        users.add(host);
        users.add(waiter);
        users.add(manager);

        // Add JPanels to JFrame
        add(dayInfo);                                                      // Add Greeting Panel to JFrame
        add(users);

        // Listeners for all the Buttons;
        cook.addActionListener(click);
        host.addActionListener(click);
        waiter.addActionListener(click);
        manager.addActionListener(click);

//        Makes window some-what "unmovable" by readjusting the location
//        addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent e) {
//                setSize(sWidth, sHeight);    // Full Screen Size
//            }
//            public void componentMoved(ComponentEvent e) {
//                setLocation(0, 0);
//            }
//        });
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == cook) {
                if (pwdVerifier.verifyPwd("cook")) {                        // If the password is correct, open CookDisplay
                    CookDisplay cDisplay = new CookDisplay();
                }
            }

            if (event.getSource() == host) {
                if (pwdVerifier.verifyPwd("host")) {
                    System.out.println("Host Password Accepted!");
                }
            }

            if (event.getSource() == waiter) {
                if (pwdVerifier.verifyPwd("waiter")) {
                    System.out.println("Waiter Password Accepted!");
                }
            }
            if (event.getSource() == manager) {
                if (pwdVerifier.verifyPwd("manager")) {
                    ManagerDisplay mDisplay = new ManagerDisplay();         // If the password is correct, open ManagerDisplay
                }
            }
        }
    }

    //                  //                  //                  //                  //

}
