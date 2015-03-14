import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.regex.Pattern;

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
        Thread updateClock = new Thread() {                                                     // New thread for Dynamic time
            public void run () {
                while (true) {
                    try {                                                                       /*** Time/Date suffix help: goo.gl/tGBKSC **/
                        Calendar cal = Calendar.getInstance();                                  // Get info from Calendar library
                        String time = String.format("%tr", cal);                                // Time format: HH:MM:SS AM|PM
                        String date = String.format("%tA, %tB %te, %tY", cal, cal, cal, cal);   // Date format: Sunday, February 1, 2015

                        // Set Message, Time, and Date on new lines
                        jLabel.setText("<html><center>"+msg+"<br>"
                                +time+"<br>"
                                +date+"</center></html>");

                        sleep(1000);                                                            // Wait 1s before re-fetching time
                    } catch (InterruptedException e) {
                        e.printStackTrace();                                                    // Show Error/Exception Message
                    }
                } // While
            } // Run
        }; // Thread
        updateClock.start();                                                                    // Start threading (updating time in seconds)
    } // Clock

    /**
     * Change font size of a component based on the screen resolution
     * @param jCmp          JComponent to update the font on (Button, Label, etc)
     * @param screenCent    Percentage of screen resolution as font size
     */
    public static void updateFont(final JComponent jCmp, double screenCent) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);
        jCmp.setFont(screenFont);
    }

    /**
     * Change font size of component(s) based on the screen resolution
     * @param screenCent    Percentage of screen resolution as font size
     * @param jCmp          JComponent(s) to update the font on (Button, Label, etc)
     */
    public static void multiUpdateFont(double screenCent, final JComponent... jCmp) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);
        for (JComponent cmp : jCmp) {
            cmp.setFont(screenFont);
        }
    }

    /**
     * Adds givens components to the container
     * @param container     JContainer to add the components to (JFrame, JPanel)
     * @param component     JComponents to add (JPanel, JButton, JLabel)
     */
    public static void multiAdd(Container container, Component... component) {
        for (Component cmp : component) {
            container.add(cmp);
        }
    }

    /**
     * Checks if a name is valid
     * @param name          Name to validate
     * @return              Result to valid or not
     */
    public static boolean validateName(final String name) {
        String regex = "^[\\p{L} .'-]+$";                                                       // http://goo.gl/GZfsnH
        return Pattern.matches(regex, name);
    }

    /**
     * Initialize Back and Quit (Buttons), and DayInfo on a frame
     * @param frame         Main JFrame to add the JPanel to
     * @param dayInfo       JPanel to add the objects to
     * @param timeDate      JLabel w/ Time and date
     * @param screenCent    Percentage of screen resolution as font size
     * @param login         Check if it's for login screen (Back Button not needed)
     */
    public static void startDayInfo(final JFrame frame, JPanel dayInfo, String msg, JLabel timeDate,
                                    double screenCent, boolean login) {
        /** Set Window/Frame's Characteristics **/
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);                                          // Keep window maximized
        frame.setLocationRelativeTo(null);                                                      // Position @ Center
        frame.setUndecorated(true);                                                             // Removes Title Bar (Disable Drag)

        dayInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc_bkQtBtn = new GridBagConstraints();                              // Constraints to Back/Quit Buttons
        gbc_bkQtBtn.weightx = 0.5;                                                              // Additional column space given
        gbc_bkQtBtn.insets = new Insets(5,5,5,5);                                               // Padding on outside

        JButton backBtn = new JButton("Back"), quitBtn = new JButton("Quit");                   // Back and Quit Buttons
        if (!login) {                                                                           // Login doesn't need back button
        // Login Button
            updateFont(backBtn, .03);
            gbc_bkQtBtn.anchor = GridBagConstraints.FIRST_LINE_START;                           // Align @ Top Left
            dayInfo.add(backBtn, gbc_bkQtBtn);

            backBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { frame.dispose(); }                 // Close window when Clicked
            });
        }

        // Quit Button
        updateFont(quitBtn, .03);
        gbc_bkQtBtn.anchor = GridBagConstraints.FIRST_LINE_END;                                 // Align @ Top Right
        dayInfo.add(quitBtn, gbc_bkQtBtn);

        quitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { System.exit(0); }                      // Quit system when Clicked
        });

        // Date and Time
        GridBagConstraints gbc_timeDate = new GridBagConstraints();
        gbc_timeDate.gridx = 0;                                                                 // Start X Cell (Top Left)
        gbc_timeDate.gridy = 0;                                                                 // Start Y Cell (Top Left)
        gbc_timeDate.gridwidth = 0;                                                             // Center Grid Width
        gbc_timeDate.insets = new Insets(0,0,5,0);                                              // Bottom Padding
        gbc_timeDate.fill = GridBagConstraints.HORIZONTAL;                                      // Expand Horizontally
        gbc_timeDate.anchor = GridBagConstraints.PAGE_START;                                    // Align @ Top Center

        startClock(timeDate, msg);                                                              // Initiate Clock w/ Greeting
        updateFont(timeDate, screenCent);
        timeDate.setHorizontalAlignment(0);                                                     // Center Date and Time (JLabel)

        dayInfo.add(timeDate, gbc_timeDate);
        frame.add(dayInfo, BorderLayout.NORTH);
    }
}
