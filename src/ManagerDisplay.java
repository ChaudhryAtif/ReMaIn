import javax.swing.*;
import java.awt.*;

public class ManagerDisplay extends JFrame {
    JPanel dayInfo = new JPanel();                                          // Top Panel
    JPanel control = new JPanel();                                          // Bottom Panel
    JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;

    public ManagerDisplay() {
        setupMDisplay();
    }

    private void setupMDisplay() {
        // Set Window/Frame's Characteristics
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLayout(new GridLayout(2, 1));                                    // Set Layout
        setLocationRelativeTo(null);                                        // Position @ Center
        setUndecorated(true);                                               // Remove Title Bar (Disable Drag)
        setVisible(true);                                                   // Show on Screen
        setResizable(false);                                                // Size is NOT adjustable (Always Maximized)

        JButton rButton = new JButton("Revenue");
        JButton cButton = new JButton("User Controls");
        JButton iButton = new JButton("Inventory");
        Utilities.updateFont(rButton, .05);
        Utilities.updateFont(cButton, .05);
        Utilities.updateFont(iButton, .05);

        Utilities.startClock(timeAndDate, "Welcome, Manager!");             // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .1);                              // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);                                           // Add Time and Date to GUI

        control.setLayout(new GridLayout(3,1));
        JPanel manButtons = new JPanel(new GridLayout());

        manButtons.add(rButton);
        manButtons.add(cButton);
        manButtons.add(iButton);
        control.add(manButtons);

        add(dayInfo);
        add(control);
    }

    public static void main(String[] args) {
        ManagerDisplay mDisplay = new ManagerDisplay();
    }
}
