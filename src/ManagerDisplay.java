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
        setUndecorated(true);                                               //\
        Color color = UIManager.getColor("activeCaptionBorder");            // Removes Title Bar (Disable Drag)
        getRootPane().setBorder(BorderFactory.createLineBorder(color, 4));  ///

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                     // Close on Exit
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);                                        // Position @ Center
        setVisible(true);                                                   // Show on Screen
        setResizable(false);                                                // Size is NOT adjustable (Always Maximized)

        JButton rButton = new JButton("Revenue");
        Utilities.updateFont(rButton, .05);
        JButton cButton = new JButton("User Controls");
        Utilities.updateFont(cButton, .05);
        JButton iButton = new JButton("Inventory");
        Utilities.updateFont(iButton, .05);

        Utilities.startClock(timeAndDate);                                  // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .1);                              // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);                                           // Add Time and Date to GUI

        control.setLayout(new GridLayout(3,1));

        JPanel manButtons = new JPanel(new GridLayout());

        manButtons.add(rButton);
        manButtons.add(cButton);
        manButtons.add(iButton);

        control.add(manButtons);
        getContentPane().add(dayInfo);
        getContentPane().add(control);
    }

    public static void main(String[] args) {
        ManagerDisplay mDisplay = new ManagerDisplay();
    }
}
