package InternshipSystem;
/**
 * Represents a generic user in the system.
 * This class serves as a base class for specific user types like students and staff.
 */
public class UserEntity {
    /**
     * The unique identifier for the user.
     */
    protected String id;

    /**
     * The name of the user.
     */
    protected String name;

    /**
     * The password for the user account.
     */
    protected String password;

    /**
     * The email address of the user.
     */
    protected String email;

    /**
     * Constructs a UserEntity with the specified ID, name, and email.
     * @param id The unique identifier for the user.
     * @param name The name of the user.
     * @param email The email address of the user.
     */
    public UserEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = "password";
    }

    /**
     * Logs in the user by verifying the provided ID and password.
     * @param inputId The ID entered by the user.
     * @param inputPw The password entered by the user.
     * @return true if the credentials are valid, false otherwise.
     */
    public boolean login(String inputId, String inputPw) {
        return id.equals(inputId) && password.equals(inputPw);
    }

    /**
     * Logs out the user.
     */
    public void logout() {
        System.out.println("Logged out successfully!");
    }

    /**
     * Changes the user's password if the old password matches.
     * @param oldPw The current password.
     * @param newPw The new password to set.
     * @return true if the password was changed successfully, false otherwise.
     */
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

    /**
     * Gets the user's ID.
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email address.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Displays the user's information.
     */
    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}
