import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDisplay extends JFrame {
    JPanel dayInfo = new JPanel();                                          // Top Section
    JPanel users = new JPanel(new GridLayout(2, 2));                        // Bottom Section
    JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;
    private JButton cook, host, waiter, manager;                            // User Buttons

    ButtonListener click = new ButtonListener();                            // Listener for Buttons
    PasswordVerifier pwdVerifier = new PasswordVerifier();                  // Initialize PasswordVerifier Class

    JButton backBtn = new JButton("Back");
    JButton quitBtn = new JButton("Quit");

    public LoginDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLocationRelativeTo(null);                                        // Position @ Center
        setUndecorated(true);                                               // Removes Title Bar (Disable Drag)
        setVisible(true);                                                   // Show on Screen
        setResizable(false);

        /** Characteristics of GBL **/
        GridBagLayout gbl_Layout = new GridBagLayout();
        gbl_Layout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_Layout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gbl_Layout);

        GridBagConstraints gbc_backBtn = new GridBagConstraints();          // Add Constraints to Back Button
        gbc_backBtn.gridx = 0;
        gbc_backBtn.gridy = 0;
        gbc_backBtn.gridwidth = 1;
        gbc_backBtn.gridheight = 1;
        gbc_backBtn.insets = new Insets(5, 5, 5, 5);
        Utilities.updateFont(backBtn, .02);
        add(backBtn, gbc_backBtn);

        GridBagConstraints gbc_quitBtn = new GridBagConstraints();          // Add Constraints to Quit Button
        gbc_quitBtn.gridx = 13;
        gbc_quitBtn.gridy = 0;
        gbc_quitBtn.insets = new Insets(5, 5, 5, 5);
        Utilities.updateFont(quitBtn, .02);
        add(quitBtn, gbc_quitBtn);

        Utilities.startClock(timeAndDate, "Welcome!");                      // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .11);                             // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);

        GridBagConstraints gbc_dayInfo = new GridBagConstraints();          // Add Constraints to Day Info Panel
        gbc_dayInfo.gridx = 0;
        gbc_dayInfo.gridy = 0;
        gbc_dayInfo.gridwidth = 13;
        gbc_dayInfo.gridheight = 3;
        gbc_dayInfo.fill = GridBagConstraints.VERTICAL;
        gbc_dayInfo.insets = new Insets(0, 0, 5, 0);
        add(dayInfo, gbc_dayInfo);


        // Create buttons for users, and update font
        cook = new JButton("Cook");
        host = new JButton("Host");
        waiter = new JButton("Waiter");
        manager = new JButton("Manager");
        Utilities.updateFont(cook, .1);
        Utilities.updateFont(host, .1);
        Utilities.updateFont(waiter, .1);
        Utilities.updateFont(manager, .1);

        // Add users to the JPanel
        users.add(cook);
        users.add(host);
        users.add(waiter);
        users.add(manager);

        GridBagConstraints gbc_users = new GridBagConstraints();           // Add Constraints to Users Panel
        gbc_users.gridx = 0;
        gbc_users.gridy = 3;
        gbc_users.gridwidth = 14;
        gbc_users.gridheight = 3;
        gbc_users.fill = GridBagConstraints.BOTH;
        gbc_users.insets = new Insets(0, 0, 5, 5);
        add(users, gbc_users);

        // Listeners for all the Buttons;
        cook.addActionListener(click);
        host.addActionListener(click);
        waiter.addActionListener(click);
        manager.addActionListener(click);
        quitBtn.addActionListener(click);

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

            if (event.getSource() == host) {                                // If the password is correct, open HostDisplay
                if (pwdVerifier.verifyPwd("host")) {
                    HostDisplay hDisplay = new HostDisplay();
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
            if (event.getSource() == quitBtn) {                             // If the quit button is pressed, quit
                System.exit(0);
            }
        }
    }
}
