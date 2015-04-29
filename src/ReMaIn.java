import java.io.File;

/** TODO LIST:
 *
 * LOGIN: Create User Class
 * ALL TABLES: Auto-Increment IDs
 * HOST: Validate reservation input before saving
 *
 * #LATER:
 * MANAGER: Allow user's password change (via User Controls)
 *
 */

class Remain {
    public static void main(String[] args) {
        File uPwd = new File("UserPass.txt");
        if (uPwd.exists() && !uPwd.isDirectory())
            new LoginDisplay();
        else
            new LaunchDisplay();
    }
}
