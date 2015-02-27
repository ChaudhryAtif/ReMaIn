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
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, backBtn, quitBtn, "Welcome, Hoster!", timeAndDate, .09, 2, false);

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
