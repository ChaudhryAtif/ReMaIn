import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CookDisplay extends JFrame {
    private JPanel bottomPane = new JPanel();
    private GridBagConstraints	gc = new GridBagConstraints();				// Layout Manager for Gridbag

    private JPanel dayInfo = new JPanel();                                          // Top Panel
    private JPanel onCall = new JPanel();
    private JPanel inventory = new JPanel();
    private JLabel timeAndDate = new JLabel();                                      // Dynamic Time & Date;
    private JTabbedPane tabbedPane = new JTabbedPane();

    private JButton backBtn = new JButton("Back");
    private JButton quitBtn = new JButton("Quit");
    private ButtonListener click = new ButtonListener();                            // Listener for Buttons

    private JLabel label = new JLabel("2nd Tab");

    public CookDisplay() {
        setupCDisplay();
    }

    private void setupCDisplay() {
        // Add Back and Quit Buttons, as well as Time and Date
        Utilities.startDayInfo(this, dayInfo, backBtn, quitBtn, "Welcome, Cook!", timeAndDate, .09, 3, false);

        JTable orderTable;
        Object[][] orderData = {
                {"111", "11", "Pizza, Juice, Fries, Juice", "3m", ""},
                {"222", "99", "Soda, Fries", "4m", ""}
        };
        Object[] colNames = {"OrderID", "Table", "Order", "Time Elapsed", "Help"};

        orderTable = new JTable(orderData, colNames);
        orderTable.setLayout(new FlowLayout());

        // Maximize table on screen
        orderTable.setPreferredScrollableViewportSize(new Dimension(Utilities.getSWidth()-30, (Utilities.getSHeight()/2)+70));
        orderTable.setFillsViewportHeight(true);
        Utilities.updateFont(orderTable, .02);												// Make font bigger
        orderTable.setRowHeight(30);														// Make rows of table bigger

        onCall.add(new JScrollPane(orderTable));											// Allows table to scroll

        inventory.add(label);

        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 15));
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>OnCall</body></html>", onCall);
        tabbedPane.add("<html><body leftmargin=15 topmargin=18 marginwidth=15 marginheight=15>Inventory</body></html>", inventory);

        GridBagConstraints gbc_tPane = new GridBagConstraints();            // Add Constraints to Control Panel
        gbc_tPane.gridx = 0;
        gbc_tPane.gridy = 3;
        gbc_tPane.gridwidth = 14;
        gbc_tPane.gridheight = 3;
        gbc_tPane.fill = GridBagConstraints.BOTH;
        gbc_tPane.insets = new Insets(0, 5, 5, 5);

        // Add action Listeners to buttons
        backBtn.addActionListener(click);
        quitBtn.addActionListener(click);
        add(tabbedPane, gbc_tPane);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // Quit Button clicked
            if (event.getSource() == quitBtn) 		{System.exit(0);}

            // Back button clicked
            if (event.getSource() == backBtn)	 	{setVisible(false);}
        }
    }

    public static void main(String[] args) {
        CookDisplay cDisplay = new CookDisplay();
    }

}
