import javax.swing.*;
import java.awt.*;

public class HostDisplay extends JFrame {
    JPanel dayInfo = new JPanel();
    JPanel tables = new JPanel();
    JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;
    JButton backBtn = new JButton("Back");
    JButton quitBtn = new JButton("Quit");

    public HostDisplay() {
        setupHDisplay();
    }

    private void setupHDisplay() {
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

        Utilities.startClock(timeAndDate, "Welcome, Hoster!");              // Initiate Clock Function to get Time and Date
        Utilities.updateFont(timeAndDate, .09);                             // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);

        GridBagConstraints gbc_dayInfo = new GridBagConstraints();          // Add Constraints to Day Info Panel
        gbc_dayInfo.gridx = 0;
        gbc_dayInfo.gridy = 0;
        gbc_dayInfo.gridwidth = 13;
        gbc_dayInfo.gridheight = 2;
        gbc_dayInfo.fill = GridBagConstraints.VERTICAL;
        gbc_dayInfo.insets = new Insets(0, 0, 5, 0);
        add(dayInfo, gbc_dayInfo);

        // Create + Set Table Row(s) Layout
        JPanel tableRowOne = new JPanel();
        JPanel tableRowTwo = new JPanel();
        JPanel tableRowThree = new JPanel();
        tableRowOne.setLayout(new GridLayout(4, 0, 0, 0));
        tableRowTwo.setLayout(new GridLayout(3, 0, 0, 0));
        tableRowThree.setLayout(new GridLayout(4, 0, 0, 0));

        // Create Table Buttons (x11)
        JButton table_01 = new JButton("Table 1");
        JButton table_02 = new JButton("Table 2");
        JButton table_03 = new JButton("Table 3");
        JButton table_04 = new JButton("Table 4");
        JButton table_05 = new JButton("Table 5");
        JButton table_06 = new JButton("Table 6");
        JButton table_07 = new JButton("Table 7");
        JButton table_08 = new JButton("Table 8");
        JButton table_09 = new JButton("Table 9");
        JButton table_10 = new JButton("Table 10");
        JButton table_11 = new JButton("Table 11");

        // Add Tables to Rows
        tableRowOne.add(table_01);
        tableRowOne.add(table_02);
        tableRowOne.add(table_03);
        tableRowOne.add(table_04);
        tableRowTwo.add(table_05);
        tableRowTwo.add(table_06);
        tableRowTwo.add(table_07);
        tableRowThree.add(table_08);
        tableRowThree.add(table_09);
        tableRowThree.add(table_10);
        tableRowThree.add(table_11);

        // Add Rows to Table Panel
        tables.add(tableRowOne);
        tables.add(tableRowTwo);
        tables.add(tableRowThree);

        GridBagConstraints gbc_tables = new GridBagConstraints();           // Add Constraints to Tables Panel
        gbc_tables.gridx = 0;
        gbc_tables.gridy = 3;
        gbc_tables.gridwidth = 14;
        gbc_tables.gridheight = 3;
        gbc_tables.fill = GridBagConstraints.BOTH;
        gbc_tables.insets = new Insets(0, 0, 5, 5);
        tables.setLayout(new GridLayout(0, 3, 0, 0));
        add(tables, gbc_tables);
    }

    public static void main(String[] args) {
        HostDisplay hDisplay = new HostDisplay();
    }
}
