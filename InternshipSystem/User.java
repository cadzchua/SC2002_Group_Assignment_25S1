package InternshipSystem;


public class User {
    protected String id;
    protected String name;
    protected String password;
    protected String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = "password"; // default
    }

    public boolean login(String inputId, String inputPw) {
        return id.equals(inputId) && password.equals(inputPw);
    }

    public void changePassword(String oldPw, String newPw) {
        if (password.equals(oldPw)) {
            password = newPw;
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Incorrect old password!");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}
