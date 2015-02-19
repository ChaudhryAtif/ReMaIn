import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Utilities {
    static int sWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();                  // Screen Width
    static int sHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();                // Screen Height

    /**
     * Start dynamic time and date (via Threading)
     * @param jLabel JLabel to set the time to
     */
    public static void startClock(final JLabel jLabel) {
        Thread updateClock = new Thread() {                                                     // New thread foe Dynamic time
            public void run () {                                                                // Automatically run (void)
                while (true) {
                    try {                                                                       /*** Time/Date suffix help: goo.gl/tGBKSC **/
                        Calendar cal = Calendar.getInstance();                                  // Get info from Calender library
                        String time = String.format("%tr", cal);                                // Time format: HH:MM:SS AM|PM
                        String date = String.format("%tA, %tB %te, %tY", cal, cal, cal, cal);   // Date format: Sunday, February 1, 2015

                        // Set Message, Time, and Date on new lines. Update Font
                        jLabel.setText("<html><center> Welcome!<br>"+time+"<br>"+date+"</center></html>");

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
     * @param jLabel     JLabel to update the font on
     * @param screenCent Percentage of screen resolution as font size
     */
    public static void updateFont(final JLabel jLabel, double screenCent) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);                              // 10% of minimum(width, height)
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);                       // Update Look

        jLabel.setFont(screenFont);
    }

    /**
     * Change font size based on the screen resolution
     * @param jButton    JButton to update the font on
     * @param screenCent Percentage of screen resolution as font size
     */
    public static void updateFont(final JButton jButton, double screenCent) {
        int FontSz = (int) (Math.min(sWidth, sHeight)*screenCent);                              // 10% of minimum(width, height)
        final Font screenFont = new Font("SansSerif", Font.BOLD, FontSz);                       // Update Look

        jButton.setFont(screenFont);
    }
}
