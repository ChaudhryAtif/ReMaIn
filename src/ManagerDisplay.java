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
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, backBtn, quitBtn, "Welcome, Manager!", timeAndDate, .09, 3, false);

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
