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

    public void showCompanyRepCLI(Scanner sc) {
        System.out.print("Enter your email: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        CompanyRep loggedIn = null;
        for (int i = 0; i < repCount; i++) {
            if (reps[i].login(id, pw) && reps[i].isApproved()) {
                loggedIn = reps[i];
                break;
            }
        }

        if (loggedIn == null) {
            System.out.println("Login failed or account not approved yet!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Company Rep Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Create Internship");
            System.out.println("3. View My Internships");
            System.out.println("4. Logout");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    loggedIn.showInfo();
                    break;
                case 2:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Level (Basic/Intermediate/Advanced): ");
                    String level = sc.nextLine();
                    System.out.print("Preferred Major: ");
                    String major = sc.nextLine();
                    System.out.print("Start Date: ");
                    String start = sc.nextLine();
                    System.out.print("End Date: ");
                    String end = sc.nextLine();
                    System.out.print("Number of slots (max 10): ");
                    int slots = sc.nextInt();
                    sc.nextLine();
                    if (slots > 10) slots = 10;

                    loggedIn.createInternship(title, desc, level, major, start, end, slots);

                    // Add to global internship array
                    for (int i = 0; i < internships.length; i++) {
                        if (internships[i] == null) {
                            internships[i] = loggedIn.getInternships()[loggedIn.getInternships().length - 1];
                            break;
                        }
                    }
                    break;
                case 3:
                    loggedIn.viewInternships();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}

