import javax.swing.*;
import java.awt.*;

public class CookDisplay extends JFrame {
    JPanel onCall = new JPanel();
    JPanel inventory = new JPanel();

    JLabel fLabel = new JLabel("1st Tab");
    JLabel sLabel = new JLabel("2nd Tab");

    JTabbedPane tabbedPane = new JTabbedPane();

    public CookDisplay() {
        setupCDisplay();
    }

    private void setupCDisplay() {
        setUndecorated(true);                                               //\
        Color color = UIManager.getColor("activeCaptionBorder");            // Removes Title Bar (Disable Drag)
        getRootPane().setBorder(BorderFactory.createLineBorder(color, 4));  ///

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                     // Close on Exit
        setExtendedState(JFrame.MAXIMIZED_BOTH);                            // Keep window maximized
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);                                        // Position @ Center
        setVisible(true);                                                   // Show on Screen
        setResizable(false);                                                // Size is NOT adjustable (Always Maximized)

        inventory.add(sLabel);
        tabbedPane.add("OnCall", onCall);
        tabbedPane.add("Inventory", inventory);

        add(tabbedPane);

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

//        JScrollPane scrollPane = new JScrollPane(orderTable);
//        orderTable.add(scrollPane);

        onCall.add(new JScrollPane(orderTable));
    }

    public static void main(String[] args) {
        CookDisplay cDisplay = new CookDisplay();
    }

}
