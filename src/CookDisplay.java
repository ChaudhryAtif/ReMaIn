import javax.swing.*;
import java.awt.*;

public class CookDisplay extends JFrame {
    JPanel dayInfo = new JPanel();                                          // Top Panel
    JPanel onCall = new JPanel();
    JPanel inventory = new JPanel();
    JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;
    JTabbedPane tabbedPane = new JTabbedPane();

    JButton backBtn = new JButton("Back");
    JButton quitBtn = new JButton("Quit");

    JLabel label = new JLabel("2nd Tab");

    public CookDisplay() {
        setupCDisplay();
    }

    private void setupCDisplay() {
        setUndecorated(true);                                               // Removes Title Bar (Disable Drag)
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLocationRelativeTo(null);                                        // Position @ Center
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

        Utilities.startClock(timeAndDate, "Welcome, Cook!");                // Initiate Clock Function to get Time and Date
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


        JTable orderTable;
        Object[][] orderData = {
                {"111", "11", "Pizza, Juice, Fries, Juice", "3m", ""},
                {"222", "99", "Soda, Fries", "4m", ""}
        };
        Object[] colNames = {"OrderID", "Table", "Order", "Time Elapsed", "Help"};

        orderTable = new JTable(orderData, colNames);
        orderTable.setLayout(new FlowLayout());

        orderTable.setPreferredScrollableViewportSize(new Dimension(500,100));
        orderTable.setFillsViewportHeight(true);

        onCall.add(new JScrollPane(orderTable));

        inventory.add(label);
        tabbedPane.add("OnCall", onCall);
        tabbedPane.add("Inventory", inventory);

        GridBagConstraints gbc_tPane = new GridBagConstraints();            // Add Constraints to Control Panel
        gbc_tPane.gridx = 0;
        gbc_tPane.gridy = 3;
        gbc_tPane.gridwidth = 14;
        gbc_tPane.gridheight = 3;
        gbc_tPane.fill = GridBagConstraints.BOTH;
        gbc_tPane.insets = new Insets(0, 0, 5, 5);
        add(tabbedPane, gbc_tPane);
    }

    public static void main(String[] args) {
        CookDisplay cDisplay = new CookDisplay();
    }

}
