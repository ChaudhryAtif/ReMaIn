public class User {
    private String job;
    private String pass;

    public User(String job, String pass){
        this.job = job;
        this.pass = pass;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
