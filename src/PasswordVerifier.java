import javax.swing.*;

public class PasswordVerifier {

    private String cPass = "cooks";
    private String hPass = "hosts";
    private String wPass = "waiters";
    private String mPass = "manages";

    private String orgPwd;                                                  // Password to compare to

    int diagResp;                                                           // Dialog response (OK, CANCEL, CLOSE)
    String givenPwd;                                                        // Stores given pass as a string
    JPasswordField pwd = new JPasswordField(10);                            // Password Field

    /**
     * Verifies password for a specific user
     * @param user  For whom to verify password for
     * @return      Result of password verification
     */
    public boolean verifyPwd(String user) {
        orgPwd = null;
        if (user.equals("cook")) { orgPwd = cPass;
        } else if (user.equals("host")) { orgPwd = hPass;
        } else if (user.equals("waiter")) { orgPwd = wPass;
        } else if (user.equals("manager")) { orgPwd = mPass;
        } else { orgPwd = null;
        }

        pwd.addAncestorListener(new RequestFocusListener(false));           // Move focus to PwdField
        do {
            pwd.setText("");                                                // Reset Pass
            diagResp = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
            givenPwd = new String(pwd.getPassword());                       // Convert given Pass to string

            if (diagResp != JOptionPane.OK_OPTION) { return false; }        // If cancel/close is clicked, exit pwd prompt
        } while (!givenPwd.equals(orgPwd));

        return true;
    }
}