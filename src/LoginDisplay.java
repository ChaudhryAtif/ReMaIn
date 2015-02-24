import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** TODO LIST:
 * MATCH cook display window to GUI design
 * CREATE waiter display window
 * CREATE manager display window
 * - ALLOW manager to change Password!
 * ADD 'Close' button to all windows (Due to No Title Bar)
 */

public class LoginDisplay extends JFrame {
    private JPanel dayInfo 			= new JPanel(new GridBagLayout());                          // Top Section
    private GridBagConstraints gc 	= new GridBagConstraints();									// Location manager 
    
    private JPanel users			= new JPanel(new GridLayout(2, 2));                       	// Bottom Section											// 
    private JLabel timeAndDate		= new JLabel();                                      		// Dynamic Time & Date;

    private JButton cook, host, waiter, manager, quitButton;
    ButtonListener click = new ButtonListener();                           				// Listener for Buttons

    PasswordVerifier pwdVerifier = new PasswordVerifier();                  			// Initialize PasswordVerifier Class

    public LoginDisplay() {
        // Set Window/Frame's Characteristics
        super();                          									 			// Constructor

        setUndecorated(true);                                               			//
        Color color = UIManager.getColor("activeCaptionBorder");           		 		// Removes Title Bar (Disable Drag)
        getRootPane().setBorder(BorderFactory.createLineBorder(color, 4));  			///

        setExtendedState(JFrame.MAXIMIZED_BOTH);                            			// Keep window maximized
        getContentPane().setLayout(new GridLayout(2, 1));                   			// Set JFrame Layout
        setVisible(true);                                                   			// Show on Screen
        setResizable(false);                                                			// Size is NOT adjustable (Always Maximized)

        Utilities.startClock(timeAndDate, "Welcome!");									// Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .11);											// Add Constraints to Day Info Panel

        // Create buttons for users, and update font
        cook = new JButton("Cook");
        host = new JButton("Host");
        waiter = new JButton("Waiter");        
        manager = new JButton("Manager");
		Utilities.updateFont(cook, .1);
		Utilities.updateFont(host, .1);
		Utilities.updateFont(waiter, .1);
		Utilities.updateFont(manager, .1);
        
        // All changes button color to red
        quitButton = new JButton("Quit");  
        
        // Sets Font size and button size
        Utilities.updateFont(quitButton, .05);

        // Add users to the JPanel
        users.add(cook);
        users.add(host);
        users.add(waiter);
        users.add(manager);
        
        // Add quit button to JPanel to control button size (Upper Left)
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(2, 0, 0, 2);
        dayInfo.add(quitButton, gc);

        // Add timeAndDate to JPanel (Center)
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(2, 0, 0, 2);
        dayInfo.add(timeAndDate, gc);

        // Listeners for all the Buttons;
        cook.addActionListener(click);
        host.addActionListener(click);
        waiter.addActionListener(click);
        manager.addActionListener(click);
        quitButton.addActionListener(click);
        
        // Add JPanels to JFrame
        add(dayInfo);
        add(users);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
        	// Cook Button Clicked
            if (event.getSource() == cook) 
            {
            	// If the password is correct, open cookDisplay
                if (pwdVerifier.verifyPwd("cook"))  	{new CookDisplay();}
            }
            
            // Host Button Clicked
            if (event.getSource() == host) 
            {
            	// If the password is correct, open HostDisplay
                if (pwdVerifier.verifyPwd("host"))  	{new HostDisplay(); }
            }

            // Waiter Button Clicked
            if (event.getSource() == waiter) 
            {
            	// If the password is correct, open WaiterDisplay
                if (pwdVerifier.verifyPwd("waiter")) 	{System.out.println("Waiter Password Accepted!");}
            }
            
            // Manager Button Clicked
            if (event.getSource() == manager) 
            {
            	// If the password is correct, open ManagerDisplay
                if (pwdVerifier.verifyPwd("manager")) 	{new ManagerDisplay();}
            }
            
            // Quit Button clicked
            if (event.getSource() == quitButton) 		{System.exit(0);}
        } // end actionPerformed
    } // end ButtonListener
} // end LoginDisplay
