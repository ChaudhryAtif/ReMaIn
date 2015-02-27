import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Utilities {
    static int sWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();           // Screen Width
    static int sHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();         // Screen Height

    /** Get Functions for Screen Width and Height **/
    public static int getSWidth() { return sWidth; }
    public static int getSHeight() { return sHeight; }

    /**
     * Start dynamic time and date (via Threading)
     * @param jLabel JLabel to set the time to)
     * @param msg    String to set the Welcome message to
     */
    public static void startClock(final JLabel jLabel, final String msg) {
        Thread updateClock = new Thread() {                                                     // New thread foe Dynamic time
            public void run () {                                                                // Automatically run (void)
                while (true) {
                    try {                                                                       /*** Time/Date suffix help: goo.gl/tGBKSC **/
                        Calendar cal = Calendar.getInstance();                                  // Get info from Calendar library
                        String time = String.format("%tr", cal);                                // Time format: HH:MM:SS AM|PM
                        String date = String.format("%tA, %tB %te, %tY", cal, cal, cal, cal);   // Date format: Sunday, February 1, 2015

                        // Set Message, Time, and Date on new lines. Update Font
                        jLabel.setText("<html><center>"+msg+"<br>"+time+"<br>"+date+"</center></html>");

                        sleep(1000);                                                            // Wait 1000ms = 1s before fetching time
                    } catch (InterruptedException e) {
                        e.printStackTrace();                                                    // Print Error/Exception Message
                    }
                } // While
            } // Run
        }; // Thread
        updateClock.start();                                                                    // Start threading (updating time in seconds)
    } // Clock

    /**
     * Change font size based on the screen resolution
     * @param jBtn          JButton to update the font on
     * @param screenCent    Percentage of screen resolution as font size
     */
    public static void updateFont(final JButton jBtn, double screenCent) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);                              // xx% of minimum(width, height)
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);                       // Update Look

        jBtn.setFont(screenFont);
    }

    /**
     * Change font size based on the screen resolution
     * @param jCmp          JComponent to update the font on
     * @param screenCent    Percentage of screen resolution as font size
     */
    public static void updateFont(final JComponent jCmp, double screenCent) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);                              // xx% of minimum(width, height)
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);                       // Update Look

        jCmp.setFont(screenFont);
    }

    /**
     * Change font size based on the screen resolution
     * @param jLbl          JLabel to update the font on
     * @param screenCent    Percentage of screen resolution as font size
     */
    public static void updateFont(final JLabel jLbl, double screenCent) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);                              // xx% of minimum(width, height)
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);                       // Update Look

        jLbl.setFont(screenFont);
    }

    /**
     * Initialize Back and Quit (Buttons), and DayInfo on a frame
     * @param frame         Main JFrame to add the JPanel to
     * @param dayInfo       JPanel to add the objects to
     * @param backBtn       Back Button
     * @param quitBtn       Quit Button
     * @param timeAndDate   JLabel w/ Time and date
     * @param screenCent    Percentage of screen resolution as font size
     * @param gHeight       Height of the GridBagLayout
     * @param login         Check if it's for login screen (Back Button not needed)
     */
    public static void startDayInfo(JFrame frame, JPanel dayInfo, JButton backBtn,
                                    JButton quitBtn, String msg, JLabel timeAndDate,
                                    double screenCent, int gHeight, boolean login) {
        /** Set Window/Frame's Characteristics **/
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);                                          // Keep window maximized
        frame.setLocationRelativeTo(null);                                                      // Position @ Center
        frame.setUndecorated(true);                                                             // Removes Title Bar (Disable Drag)
        frame.setVisible(true);                                                                 // Show on Screen
        frame.setResizable(false);                                                              // Size is NOT adjustable (Always Maximized)

        /** Characteristics of GBL **/
        GridBagLayout gbl_Layout = new GridBagLayout();
        gbl_Layout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_Layout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        frame.setLayout(gbl_Layout);

        if (!login) {                                                                           // Login doesn't need back button
            GridBagConstraints gbc_backBtn = new GridBagConstraints();                          // Add Constraints to Back Button
            gbc_backBtn.gridx = 0;
            gbc_backBtn.gridy = 0;
            gbc_backBtn.gridwidth = 1;
            gbc_backBtn.gridheight = 1;
            gbc_backBtn.insets = new Insets(5, 5, 5, 5);
            updateFont(backBtn, .03);
            frame.add(backBtn, gbc_backBtn);
        }

        GridBagConstraints gbc_quitBtn = new GridBagConstraints();                              // Add Constraints to Quit Button
        gbc_quitBtn.gridx = 13;
        gbc_quitBtn.gridy = 0;
        gbc_quitBtn.insets = new Insets(5, 5, 5, 5);
        updateFont(quitBtn, .03);
        frame.add(quitBtn, gbc_quitBtn);

        startClock(timeAndDate, msg);                                                           // Initiate Clock Function to get Time and Date
        updateFont(timeAndDate, screenCent);                                                    // Update font (10% of minimum screen resolution
        dayInfo.add(timeAndDate);

        GridBagConstraints gbc_dayInfo = new GridBagConstraints();                              // Add Constraints to Day Info Panel
        gbc_dayInfo.gridx = 0;
        gbc_dayInfo.gridy = 0;
        gbc_dayInfo.gridwidth = 13;
        gbc_dayInfo.gridheight = gHeight;
        gbc_dayInfo.fill = GridBagConstraints.VERTICAL;
        gbc_dayInfo.insets = new Insets(0, 0, 5, 0);
        frame.add(dayInfo, gbc_dayInfo);
    }
}
