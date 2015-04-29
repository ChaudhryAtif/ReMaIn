import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class LaunchDisplay extends JFrame {
    private JPanel greetPanel = new JPanel(new BorderLayout()), setupPanel = new JPanel(new BorderLayout());
    private JPanel userPanel, setupCtrls = new JPanel();
    private JLabel userName;
    private JButton showPassToggle, userConfirm;
    private JButton startSetup = new JButton("Start Setup"), quitSetup = new JButton("Quit Setup");
    private JPasswordField userPassOne, userPassTwo;

    private boolean firstLaunch = true;
    private Writer writer;
    private int userCount = 0;
    private boolean showPass = false;
    private ButtonListener click = new ButtonListener();

    public LaunchDisplay() { setupLDisplay(); }

    public void setupLDisplay() {
        setLayout(new GridLayout(2,0));
        greetPanel.setLayout(new BorderLayout());

        JLabel welcomeMsg = new JLabel("<html>Welcome to ReMaIn registration!<br><br>" +
                "- Click Start Setup to continue<br>" +
                "- Quit Setup to cancel registration!</html>", SwingConstants.CENTER);
        welcomeMsg.setHorizontalAlignment(0);
        greetPanel.add(welcomeMsg, BorderLayout.CENTER);
        Utilities.updateFont(welcomeMsg, .075);

        Utilities.multiAdd(setupCtrls, startSetup, Box.createRigidArea(new Dimension(10, 0)), quitSetup);

        /** User Login Display (Shown via Button Click) **/
        userPanel = new JPanel(new MigLayout("fillx, bottom"));
        userName = new JLabel("Manager");
        JLabel createPassLbl = new JLabel("Create Password ");
        JLabel confirmPassLbl = new JLabel("Verify Password ");
        userPassOne = new JPasswordField(10);
        userPassTwo = new JPasswordField(10);
        showPassToggle = new JButton("Show");
        userConfirm = new JButton("Confirm");

        userPanel.add(new JLabel("-"), "wrap"); //  \
        userPanel.add(new JLabel("-"), "wrap"); //  |-> Add Spacing
        userPanel.add(new JLabel("-"), "wrap"); //  /

        userPanel.add(userName, "center, wrap");
        userPanel.add(createPassLbl, "center, sg 1, split");
        userPanel.add(userPassOne, "center, wrap");
        userPanel.add(confirmPassLbl, "center, sg 1, split");
        userPanel.add(userPassTwo, "center, wrap");
        userPanel.add(showPassToggle, "center, gapleft 110, split 2");
        userPanel.add(userConfirm);

        Utilities.multiUpdateFont(.035, userName);
        Utilities.multiUpdateFont(.025, createPassLbl, confirmPassLbl, startSetup);
        Utilities.multiUpdateFont(.020, showPassToggle, userConfirm, quitSetup);

        // Close Display, and Delete file upon quit
        quitSetup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!firstLaunch) { writer.close(); }
                    new File("UserPass.txt").delete();
                    System.exit(0);
                } catch (Exception ex) { ex.printStackTrace(); }}                      // Quit system when Clicked
        });
        startSetup.addActionListener(click);
        showPassToggle.addActionListener(click);
        userConfirm.addActionListener(click);
        // Attach 'Enter' Key to Confirm Button while entering password(s)
        userPassOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { userConfirm.doClick(); }
        });
        userPassTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { userConfirm.doClick(); }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);                                          // Keep window maximized
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);                             // Disable ALT+F4 Close
        setUndecorated(true);

        greetPanel.add(setupCtrls, BorderLayout.SOUTH);
        Utilities.multiAdd(this, greetPanel, setupPanel);

        setVisible(true);
        setResizable(false);
    }

    /**
     * ButtonListener implementation to respond to button clicks
     */
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Initiate display to start password creation
            if (event.getSource() == startSetup) {
                startSetup.setEnabled(false);
                firstLaunch = false;
                setupPanel.add(userPanel, BorderLayout.NORTH);
                userPassOne.requestFocusInWindow();
                revalidate();

                try {
                    writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("UserPass.txt"), "UTF-8"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (event.getSource() == showPassToggle) {
                userPassOne.requestFocusInWindow();
                if (!showPass) {
                    userPassOne.setEchoChar((char) 0);
                    userPassTwo.setEchoChar((char) 0);

                    showPass = true;
                    showPassToggle.setText(" Hide ");
                }
                else {
                    userPassOne.setEchoChar(('•'));
                    userPassTwo.setEchoChar(('•'));

                    showPass = false;
                    showPassToggle.setText("Show");
                }
            }
            // Verify Pass, Write to File, Increment User, Close Writer, and launch Login Display
            if (event.getSource() == userConfirm) {
                if (userPassOne.getPassword().length == 0 || userPassTwo.getPassword().length == 0 ||
                        !Arrays.equals(userPassOne.getPassword(), userPassTwo.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Password fields do not match!\n" +
                            "Please try again!", "Password Error!", JOptionPane.ERROR_MESSAGE);
                    userPassOne.setText("");
                    userPassTwo.setText("");

                    userPassOne.requestFocusInWindow();
                } else if (Arrays.equals(userPassOne.getPassword(), userPassTwo.getPassword())) {
                    String securePass = PasswordVerifier.getSecurePass(userPassOne.getPassword());

                    try {
                        switch (userCount) {
                            case 0:
                                writer.write(securePass);
                                break;
                            case 1:
                                writer.write("," + securePass);
                                break;
                            case 2:
                                writer.write("," + securePass);
                                break;
                            case 3:
                                writer.write("," + securePass);
                                break;
                            default:
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    userCount++;
                    userPassOne.setText("");
                    userPassTwo.setText("");
                    if (userCount == 1) {
                        showPass = true;
                        showPassToggle.doClick();
                        userName.setText("Cook");
                    }
                    if (userCount == 2) {
                        showPass = true;
                        showPassToggle.doClick();
                        userName.setText("Host");
                    }
                    if (userCount == 3) {
                        showPass = true;
                        showPassToggle.doClick();
                        userName.setText("Waiter");
                        userConfirm.setText("Launch ");
                    }
                    if (userCount == 4) {
                        userCount = 0;
                        try {
                            writer.close();
                        } catch (Exception ex) { ex.printStackTrace(); }

                        new LoginDisplay();
                        dispose();
                    }
                    revalidate();
                }
            }
        }
    }

    public static void main(String[] args) {
        new LaunchDisplay();
    }
}
