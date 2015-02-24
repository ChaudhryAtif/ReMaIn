import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CookDisplay extends JFrame 
{
    private JPanel dayInfo;                 	// Top Panel
    private JPanel bottomPane;
    private GridBagConstraints	gc;				// Layout Manager for Gridbag
    
    private JPanel onCall;						
    private JPanel inventory;
    private JLabel timeAndDate;                 // Dynamic Time & Date;
    private JTabbedPane tabbedPane;

    private JButton backButton;					// Button to back to Login screen
    private JButton quitButton;					// Button to exit app
    private ButtonListener click;				// Action listener to detect click

    private JLabel label = new JLabel("2nd Tab");

    public CookDisplay() 	{setupCDisplay();}

    private void setupCDisplay() 
    {
    	setLayout(new GridLayout(2,1));										// Grid Layout cuts page Horizontally
        setUndecorated(true);                                               // Removes Title Bar (Disable Drag)
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setVisible(true);                                                   // Show on Screen
        setResizable(false);                                                // Size is NOT adjustable (Always Maximized)
        
        // Initialize private Variables
        dayInfo 		= new JPanel(new GridBagLayout());					// Initializes the Gridbag layout
        bottomPane		= new JPanel();
        gc				= new GridBagConstraints();
        onCall 			= new JPanel();
        inventory 		= new JPanel();
        timeAndDate		= new JLabel(); 
        tabbedPane		= new JTabbedPane();
        click			= new ButtonListener();
        label			= new JLabel("2nd Tab");
        
        Utilities.startClock(timeAndDate, "Welcome, Cook!");                // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .09);                             // Update font (10% of minimum screen resolution
        
        // Initialize Back button
        backButton 		= new JButton("Back");
        Utilities.updateFont(backButton, .05);								// set font size larger
        
        // Add back button to JPanel to control button size (Upper Left)
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(2, 0, 0, 2);
        dayInfo.add(backButton, gc);
        
        // Initialize quit button
        quitButton 		= new JButton("Quit");
        Utilities.updateFont(quitButton, .05); 								// Set font size larger
        
        // Add quit button to JPanel to control button size (Upper Right)
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTHEAST;
        gc.insets = new Insets(2, 0, 0, 2);
        dayInfo.add(quitButton, gc);

        // Add timeAndDate to JPanel (Center)
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(2, 0, 0, 2);
        dayInfo.add(timeAndDate, gc);


        JTable orderTable;
        Object[][] orderData = {
                {"111", "11", "Pizza, Juice, Fries, Juice", "3m", ""},
                {"222", "99", "Soda, Fries", "4m", ""}
        };
        Object[] colNames = {"OrderID", "Table", "Order", "Time Elapsed", "Help"};

        orderTable = new JTable(orderData, colNames);
        orderTable.setLayout(new FlowLayout());

        // Set the table to be as big as possible on screen
        orderTable.setPreferredScrollableViewportSize(new Dimension(Utilities.getSWidth()-35, (Utilities.getSHeight()/2)-87));
        
        orderTable.setFillsViewportHeight(true);											
        Utilities.updateFont(orderTable, .02);												// Make font bigger
        orderTable.setRowHeight(30);														// Make rows of table bigger

        onCall.add(new JScrollPane(orderTable));											// Allows table to scroll

        inventory.add(label);
        
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 15));
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>OnCall</body></html>", onCall);
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>Inventory</body></html>", inventory);
        
        bottomPane.add(tabbedPane);
        
        // Add action Listeners to buttons
        quitButton.addActionListener(click);
        backButton.addActionListener(click);
        
        // Add top and bottom to JFrame
        add(dayInfo);
        add(bottomPane);
    }
    
    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            // Quit Button clicked
            if (event.getSource() == quitButton) 		{System.exit(0);}
            
            // Back button clicked
            if (event.getSource() == backButton)	 	{setVisible(false);}
        } // end actionPerformed
    } // end ButtonListener

    public static void main(String[] args) {new CookDisplay();}

}
