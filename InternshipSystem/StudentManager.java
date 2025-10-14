package InternshipSystem;

import java.util.Scanner;

public class StudentManager {

    private Student[] students;
    private Internship[] internships;
    private int studentCount;

    public StudentManager(Student[] students, int studentCount, Internship[] internships) {
        this.students = students;
        this.studentCount = studentCount;
        this.internships = internships;
    }

    public void showStudentCLI(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        Student loggedIn = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].login(id, pw)) {
                loggedIn = students[i];
                break;
            }
        }

        if (loggedIn == null) {
            System.out.println("Login failed!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. View Internships");
            System.out.println("3. Apply for Internship");
            System.out.println("4. View Applied Internships");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    loggedIn.showInfo();
                    break;
                case 2:
                    System.out.println("Available Internships:");
                    for (Internship in : internships) {
                        if (in != null && in.getStatus().equals("Approved")) {
                            in.showInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Internship Title to apply: ");
                    String title = sc.nextLine();
                    loggedIn.applyInternship(title, "Basic"); // simplified
                    break;
                case 4:
                    loggedIn.viewApplications();
                    break;
                case 5:
                    System.out.print("Enter old password: ");
                    String oldPw = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPw = sc.nextLine();
                    loggedIn.changePassword(oldPw, newPw);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }
}

