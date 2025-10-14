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

        System.out.println("Login successful! Welcome, " + loggedIn.getName());

        int choice;
        do {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. View Available Internships");
            System.out.println("3. Apply for Internship");
            System.out.println("4. View My Applications");
            System.out.println("5. Accept Internship Placement");
            System.out.println("6. Request Withdrawal");
            System.out.println("7. Change Password");
            System.out.println("8. Logout");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    loggedIn.showInfo();
                    break;
                    
                case 2:
                    System.out.println("\n=== Available Internships ===");
                    boolean found = false;
                    for (Internship internship : internships) {
                        if (internship != null && 
                            internship.isVisible() && 
                            internship.isEligibleForStudent(loggedIn) &&
                            !internship.getStatus().equals("Filled")) {
                            internship.showInfo();
                            System.out.println("------------------------");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No internships available for your profile.");
                    }
                    break;
                    
                case 3:
                    if (!loggedIn.canApply()) {
                        System.out.println("You cannot apply for more internships.");
                        break;
                    }
                    System.out.print("Enter Internship Title to apply: ");
                    String title = sc.nextLine();
                    
                    Internship selectedInternship = null;
                    for (Internship internship : internships) {
                        if (internship != null && internship.getTitle().equals(title)) {
                            selectedInternship = internship;
                            break;
                        }
                    }
                    
                    if (selectedInternship != null) {
                        loggedIn.applyInternship(selectedInternship);
                    } else {
                        System.out.println("Internship not found.");
                    }
                    break;
                    
                case 4:
                    loggedIn.viewApplications();
                    break;
                    
                case 5:
                    System.out.print("Enter Internship Title to accept: ");
                    String acceptTitle = sc.nextLine();
                    loggedIn.acceptPlacement(acceptTitle);
                    
                    // Update internship filled slots
                    for (Internship internship : internships) {
                        if (internship != null && internship.getTitle().equals(acceptTitle)) {
                            internship.confirmPlacement();
                            break;
                        }
                    }
                    break;
                    
                case 6:
                    System.out.print("Enter Internship Title to withdraw from: ");
                    String withdrawTitle = sc.nextLine();
                    loggedIn.requestWithdrawal(withdrawTitle);
                    break;
                    
                case 7:
                    System.out.print("Enter old password: ");
                    String oldPw = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPw = sc.nextLine();
                    loggedIn.changePassword(oldPw, newPw);
                    break;
                    
                case 8:
                    loggedIn.logout();
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }
}

