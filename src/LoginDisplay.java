import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoginDisplay extends JFrame {
    private JPanel dayInfo = new JPanel();                                  // Top Half of Frame
    private JPanel users = new JPanel(new GridLayout(2, 2));                // Bottom Half of Frame
    private JLabel timeDate = new JLabel();                                 // Dynamic Time & Date;
    private JButton cook, host, waiter, manager;                            // User Buttons

    private ButtonListener click = new ButtonListener();
    private PasswordVerifier pwdVerifier = new PasswordVerifier();

    public LoginDisplay() {
        /** Add Quit Button, as well as Time and Date **/
        Utilities.startDayInfo(this, dayInfo, "Welcome!", timeDate, .11, true);

        // Create instances of User Class
        User cook_user = new User("Cook","cooks");
        User host_user = new User("Host","hosts");
        User waiter_user = new User("Waiter","waiters");
        User manager_user = new User("Manager","manages");

        // Create and Update buttons
        cook = new JButton(cook_user.getJob());
        host = new JButton(host_user.getJob());
        waiter = new JButton(waiter_user.getJob());
        manager = new JButton(manager_user.getJob());
        Utilities.multiUpdateFont(.1, cook, host, waiter, manager);

        // Add Buttons (users) to Users Panel, and Users to Frame
        Utilities.multiAdd(users, waiter, host, cook, manager);
        add(users, BorderLayout.CENTER);

        // Add Listeners for all the Buttons;
        cook.addActionListener(click);
        host.addActionListener(click);
        waiter.addActionListener(click);
        manager.addActionListener(click);

        /** Set Visible Last To Avoid Glitches/Flickering **/
        setVisible(true);
        setResizable(false);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            pwdVerifier.readFile(new File("UserPass.txt"));

            if (event.getSource() == manager) {
                if (pwdVerifier.verifyPwd("manager")) { new ManagerDisplay(); }   // If correct pass, open Display
            }
            if (event.getSource() == cook) {
                if (pwdVerifier.verifyPwd("cook")) { new CookDisplay(); }
            }
            if (event.getSource() == host) {
                if (pwdVerifier.verifyPwd("host")) { new HostDisplay(); }
            }
            if (event.getSource() == waiter) {
                if (pwdVerifier.verifyPwd("waiter")) { new WaiterDisplay(); }
            }
        }
    } // ButtonListener
}
