import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDisplay extends JFrame {
    private JPanel dayInfo = new JPanel();                                  // Top Section
    private JPanel users = new JPanel(new GridLayout(2, 2));                // Bottom Section
    private JLabel timeAndDate = new JLabel();                              // Dynamic Time & Date;
    private JButton cook, host, waiter, manager;                            // User Buttons

    private ButtonListener click = new ButtonListener();                    // Listener for Buttons
    private PasswordVerifier pwdVerifier = new PasswordVerifier();          // Initialize PasswordVerifier Class

    private JButton quitBtn = new JButton("Quit");

    public LoginDisplay() {
        /** Add Quit Button, as well as Time and Date **/
        Utilities.startDayInfo(this, dayInfo, null, quitBtn, "Welcome!", timeAndDate, .11, 3, true);

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
