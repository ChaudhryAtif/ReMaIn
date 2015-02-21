import javax.swing.*;
import java.awt.*;

public class ManagerDisplay extends JFrame {
    JPanel dayInfo = new JPanel();                                          // Top Panel
    JPanel control = new JPanel();                                          // Bottom Panel
    JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;

    JButton backBtn = new JButton("Back");
    JButton quitBtn = new JButton("Quit");

    public ManagerDisplay() {
        setupMDisplay();
    }

    private void setupMDisplay() {
        // Set Window/Frame's Characteristics
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLocationRelativeTo(null);                                        // Position @ Center
        setUndecorated(true);                                               // Remove Title Bar (Disable Drag)
        setVisible(true);                                                   // Show on Screen
        setResizable(false);                                                // Size is NOT adjustable (Always Maximized)

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

        Utilities.startClock(timeAndDate, "Welcome, Manager!");             // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .09);                             // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);

        GridBagConstraints gbc_dayInfo = new GridBagConstraints();          // Add Constraints to Day Info Panel
        gbc_dayInfo.gridx = 0;
        gbc_dayInfo.gridy = 0;
        gbc_dayInfo.gridwidth = 13;
        gbc_dayInfo.gridheight = 3;
        gbc_dayInfo.fill = GridBagConstraints.VERTICAL;
        gbc_dayInfo.insets = new Insets(0, 0, 5, 0);
        add(dayInfo, gbc_dayInfo);

        JButton rButton = new JButton("Revenue");
        JButton cButton = new JButton("User Controls");
        JButton iButton = new JButton("Inventory");
        Utilities.updateFont(rButton, .065);
        Utilities.updateFont(cButton, .065);
        Utilities.updateFont(iButton, .065);

        control.setLayout(new GridLayout(3,1));
        JPanel manButtons = new JPanel(new GridLayout());

        manButtons.add(rButton);
        manButtons.add(cButton);
        manButtons.add(iButton);
        control.add(manButtons);

        GridBagConstraints gbc_control = new GridBagConstraints();           // Add Constraints to Control Panel
        gbc_control.gridx = 0;
        gbc_control.gridy = 4;
        gbc_control.gridwidth = 14;
        gbc_control.gridheight = 2;
        gbc_control.fill = GridBagConstraints.BOTH;
        gbc_control.insets = new Insets(0, 0, 5, 5);
        add(control, gbc_control);
    }

    public static void main(String[] args) {
        ManagerDisplay mDisplay = new ManagerDisplay();
    }
}
