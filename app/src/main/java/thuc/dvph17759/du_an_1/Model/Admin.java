package thuc.dvph17759.du_an_1.Model;

public class Admin {
    String name;
    String pass;

    public Admin(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public Admin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
