package InternshipSystem;

import java.util.Scanner;

public class StudentControl {

    private StudentEntity[] students;
    private InternshipEntity[] internships;
    private int studentCount;

    public StudentControl(StudentEntity[] students, int studentCount, InternshipEntity[] internships) {
        this.students = students;
        this.studentCount = studentCount;
        this.internships = internships;
    }

    public void showStudentCLI(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        StudentEntity loggedIn = null;
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
                    InternshipEntity[] availableInternships = new InternshipEntity[100];
                    int availableCount = 0;
                    for (InternshipEntity internship : internships) {
                        if (internship != null && 
                            internship.isVisible() && 
                            internship.isEligibleForStudent(loggedIn) &&
                            !internship.getStatus().equals("Filled") &&
                            (loggedIn.getAcceptedInternship() == null || 
                             !loggedIn.getAcceptedInternship().equals(internship.getTitle()))) {
                            availableInternships[availableCount++] = internship;
                        }
                    }
                    
                    if (availableCount == 0) {
                        System.out.println("No internships available for your profile.");
                    } else {
                        for (int i = 0; i < availableCount; i++) {
                            System.out.println("\n[" + (i + 1) + "]");
                            availableInternships[i].showInfo();
                            System.out.println("------------------------");
                        }
                    }
                    break;
                    
                case 3:
                    if (!loggedIn.canApply()) {
                        System.out.println("You cannot apply for more internships.");
                        break;
                    }
                    
                    InternshipEntity[] applyInternships = new InternshipEntity[100];
                    int applyCount = 0;
                    for (InternshipEntity internship : internships) {
                        if (internship != null && 
                            internship.isVisible() && 
                            internship.isEligibleForStudent(loggedIn) &&
                            !internship.getStatus().equals("Filled") &&
                            (loggedIn.getAcceptedInternship() == null || 
                             !loggedIn.getAcceptedInternship().equals(internship.getTitle()))) {
                            applyInternships[applyCount++] = internship;
                        }
                    }
                    
                    if (applyCount == 0) {
                        System.out.println("No internships available to apply for.");
                        break;
                    }
                    
                    System.out.println("\n=== Select Internship to Apply ===");
                    for (int i = 0; i < applyCount; i++) {
                        System.out.println((i + 1) + ". " + applyInternships[i].getTitle() + 
                                         " (" + applyInternships[i].getLevel() + " - " + 
                                         applyInternships[i].getCompanyName() + ")");
                    }
                    System.out.print("Enter number (or 0 to cancel): ");
                    int applyChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (applyChoice > 0 && applyChoice <= applyCount) {
                        loggedIn.applyInternship(applyInternships[applyChoice - 1]);
                    } else if (applyChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 4:
                    loggedIn.viewApplications();
                    break;
                    
                case 5:
                    System.out.println("\n=== Your Internship Applications ===");
                    int displayCount = 0;
                    int[] validIndexes = new int[3];
                    for (int i = 0; i < loggedIn.getAppCount(); i++) {
                        String appTitle = loggedIn.getApplicationTitle(i);
                        String appStatus = loggedIn.getApplicationStatus()[i];
                        if (appTitle != null && appStatus.equals("Successful") &&
                            (loggedIn.getAcceptedInternship() == null || 
                             !loggedIn.getAcceptedInternship().equals(appTitle))) {
                            validIndexes[displayCount] = i;
                            System.out.println((displayCount + 1) + ". " + appTitle + " - Status: " + appStatus);
                            displayCount++;
                        }
                    }
                    
                    if (displayCount == 0) {
                        System.out.println("No successful applications available to accept.");
                        break;
                    }
                    
                    System.out.print("\nEnter the application number to accept (or 0 to cancel): ");
                    int acceptChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (acceptChoice > 0 && acceptChoice <= displayCount) {
                        String acceptTitle = loggedIn.getApplicationTitle(validIndexes[acceptChoice - 1]);
                        if (acceptTitle != null) {
                            loggedIn.acceptPlacement(acceptTitle);
                            
                            for (InternshipEntity internship : internships) {
                                if (internship != null && internship.getTitle().equals(acceptTitle)) {
                                    internship.confirmPlacement();
                                    break;
                                }
                            }
                        } else {
                            System.out.println("No application at that number.");
                        }
                    } else if (acceptChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 6:
                    loggedIn.viewApplications();
                    System.out.print("\nEnter the application number to withdraw from (or 0 to cancel): ");
                    int withdrawChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (withdrawChoice > 0 && withdrawChoice <= 3) {
                        String withdrawTitle = loggedIn.getApplicationTitle(withdrawChoice - 1);
                        if (withdrawTitle != null) {
                            loggedIn.requestWithdrawal(withdrawTitle);
                        } else {
                            System.out.println("No application at that number.");
                        }
                    } else if (withdrawChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
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
