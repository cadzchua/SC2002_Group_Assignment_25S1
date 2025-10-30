package InternshipSystem;


public class UserEntity {
    protected String id;
    protected String name;
    protected String password;
    protected String email;

    public UserEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = "password";
    }

    public boolean login(String inputId, String inputPw) {
        return id.equals(inputId) && password.equals(inputPw);
    }

    public void logout() {
        System.out.println("Logged out successfully!");
    }

    public boolean changePassword(String oldPw, String newPw) {
        if (password.equals(oldPw)) {
            password = newPw;
            System.out.println("Password changed successfully!");
            System.out.println("Please log in again with your new password.");
            return true;
        } else {
            System.out.println("Incorrect old password!");
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}
