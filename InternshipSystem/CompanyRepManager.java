package InternshipSystem;

import java.util.Scanner;

public class CompanyRepManager {

    private CompanyRep[] reps;
    private int repCount;
    private Internship[] internships;

    public CompanyRepManager(CompanyRep[] reps, int repCount, Internship[] internships) {
        this.reps = reps;
        this.repCount = repCount;
        this.internships = internships;
    }

    public void registerCompanyRep(Scanner sc) {
        System.out.println("\n=== Company Representative Registration ===");
        System.out.print("Enter your ID: ");
        String id = sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter company name: ");
        String companyName = sc.nextLine();
        System.out.print("Enter department: ");
        String department = sc.nextLine();
        System.out.print("Enter position: ");
        String position = sc.nextLine();

        if (repCount < reps.length) {
            reps[repCount++] = new CompanyRep(id, name, companyName, department, position, email);
            
            // Save to CSV file permanently
            // CSV format: CompanyRepID,Name,CompanyName,Department,Position,Email,Status
            String csvLine = id + "," + name + "," + companyName + "," + department + "," + position + "," + email + ",Pending";
            CSVReader.appendCSV("sample_company_representative_list.csv", csvLine);
            
            System.out.println("Registration submitted! Awaiting approval from Career Center Staff.");
        } else {
            System.out.println("Maximum number of company representatives reached.");
        }
    }

    public void showCompanyRepCLI(Scanner sc) {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.print("Select option: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 2) {
            registerCompanyRep(sc);
            return;
        }

        System.out.print("Enter your ID: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        CompanyRep loggedIn = null;
        for (int i = 0; i < repCount; i++) {
            if (reps[i].login(id, pw)) {
                if (!reps[i].isApproved()) {
                    System.out.println("Your account is pending approval from Career Center Staff.");
                    return;
                }
                loggedIn = reps[i];
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
            System.out.println("\n=== Company Representative Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. Create Internship");
            System.out.println("3. View My Internships");
            System.out.println("4. View Applications for Internship");
            System.out.println("5. Approve/Reject Application");
            System.out.println("6. Toggle Internship Visibility");
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
                    System.out.print("Internship Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Level (Basic/Intermediate/Advanced): ");
                    String level = sc.nextLine();
                    System.out.print("Preferred Major: ");
                    String major = sc.nextLine();
                    System.out.print("Opening Date (YYYY-MM-DD): ");
                    String openingDate = sc.nextLine();
                    System.out.print("Closing Date (YYYY-MM-DD): ");
                    String closingDate = sc.nextLine();
                    System.out.print("Number of slots (max 10): ");
                    int slots = sc.nextInt();
                    sc.nextLine();

                    loggedIn.createInternship(title, desc, level, major, openingDate, closingDate, slots);

                    // Add to global internship array
                    for (int i = 0; i < internships.length; i++) {
                        if (internships[i] == null) {
                            Internship[] repInternships = loggedIn.getInternships();
                            for (int j = 0; j < loggedIn.getInternshipCount(); j++) {
                                if (repInternships[j] != null && repInternships[j].getTitle().equals(title)) {
                                    internships[i] = repInternships[j];
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                    
                case 3:
                    loggedIn.viewInternships();
                    break;
                    
                case 4:
                    System.out.print("Enter Internship Title: ");
                    String viewTitle = sc.nextLine();
                    loggedIn.viewApplicationsForInternship(viewTitle);
                    break;
                    
                case 5:
                    System.out.print("Enter Internship Title: ");
                    String appTitle = sc.nextLine();
                    System.out.print("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    System.out.print("Approve or Reject? (A/R): ");
                    String decision = sc.nextLine();
                    
                    if (decision.equalsIgnoreCase("A")) {
                        loggedIn.approveApplication(appTitle, studentId);
                    } else if (decision.equalsIgnoreCase("R")) {
                        loggedIn.rejectApplication(appTitle, studentId);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                    
                case 6:
                    System.out.print("Enter Internship Title: ");
                    String toggleTitle = sc.nextLine();
                    loggedIn.toggleInternshipVisibility(toggleTitle);
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

    public CompanyRep[] getReps() {
        return reps;
    }

    public int getRepCount() {
        return repCount;
    }
}

