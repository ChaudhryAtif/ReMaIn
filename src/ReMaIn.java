import java.io.File;

class Remain {
    public static void main(String[] args) {
        File uPwd = new File("UserPass.txt");
        if (uPwd.exists() && !uPwd.isDirectory())
            new LoginDisplay();
        else
            new LaunchDisplay();
    }
}
