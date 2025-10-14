package InternshipSystem;

import java.util.Scanner;

public class StaffManager {

    private CareerCentreStaff[] staffList;
    private int staffCount;
    private CompanyRep[] companyReps;
    private int repCount;

    public StaffManager(CareerCentreStaff[] staffList, int staffCount, CompanyRep[] companyReps, int repCount) {
        this.staffList = staffList;
        this.staffCount = staffCount;
        this.companyReps = companyReps;
        this.repCount = repCount;
    }

    public void showStaffCLI(Scanner sc) {
        System.out.print("Enter Staff ID: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        CareerCentreStaff loggedIn = null;
        for (int i = 0; i < staffCount; i++) {
            if (staffList[i].login(id, pw)) {
                loggedIn = staffList[i];
                break;
            }
        }

        if (loggedIn == null) {
            System.out.println("Login failed!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Approve Company Reps");
            System.out.println("3. Logout");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    loggedIn.showInfo();
                    break;
                case 2:
                    System.out.println("Pending Company Reps:");
                    for (int i = 0; i < repCount; i++) {
                        if (!companyReps[i].isApproved()) {
                            companyReps[i].showInfo();
                            System.out.print("Approve? (yes/no): ");
                            String ans = sc.nextLine();
                            if (ans.equalsIgnoreCase("yes")) {
                                loggedIn.approveCompanyRep(companyReps[i]);
                                System.out.println("Approved!");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);
    }
}
