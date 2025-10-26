package InternshipSystem;

import java.util.Scanner;

public class StaffManager {

    private CareerCentreStaff[] staffList;
    private int staffCount;
    private CompanyRep[] companyReps;
    private int repCount;
    private Internship[] internships;
    private Student[] students;
    private int studentCount;

    public StaffManager(CareerCentreStaff[] staffList, int staffCount, CompanyRep[] companyReps, 
                        int repCount, Internship[] internships, Student[] students, int studentCount) {
        this.staffList = staffList;
        this.staffCount = staffCount;
        this.companyReps = companyReps;
        this.repCount = repCount;
        this.internships = internships;
        this.students = students;
        this.studentCount = studentCount;
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

        System.out.println("Login successful! Welcome, " + loggedIn.getName());

        int choice;
        do {
            System.out.println("\n=== Career Center Staff Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. Approve/Reject Company Representatives");
            System.out.println("3. Approve/Reject Internship Opportunities");
            System.out.println("4. Handle Student Withdrawal Requests");
            System.out.println("5. Generate Internship Reports");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    loggedIn.showInfo();
                    break;
                    
                case 2:
                    System.out.println("\n=== Pending Company Representatives ===");
                    boolean foundPending = false;
                    for (int i = 0; i < repCount; i++) {
                        if (companyReps[i] != null && !companyReps[i].isApproved()) {
                            foundPending = true;
                            System.out.println("\n--- Representative " + (i+1) + " ---");
                            companyReps[i].showInfo();
                            System.out.print("Approve this representative? (Y/N): ");
                            String ans = sc.nextLine();
                            if (ans.equalsIgnoreCase("Y")) {
                                loggedIn.approveCompanyRep(companyReps[i]);
                            } else {
                                loggedIn.rejectCompanyRep(companyReps[i]);
                            }
                            System.out.println();
                        }
                    }
                    if (!foundPending) {
                        System.out.println("No pending company representatives.");
                    }
                    loggedIn.updateCompanyRepCSV(companyReps, repCount);
                    break;
                    
                case 3:
                    System.out.println("\n=== Pending Internship Opportunities ===");
                    boolean foundPendingInternships = false;
                    for (Internship internship : internships) {
                        if (internship != null && internship.getStatus().equals("Pending")) {
                            foundPendingInternships = true;
                            internship.showInfo();
                            System.out.print("Approve this internship? (Y/N): ");
                            String ans = sc.nextLine();
                            if (ans.equalsIgnoreCase("Y")) {
                                loggedIn.approveInternship(internship);
                            } else {
                                loggedIn.rejectInternship(internship);
                            }
                            System.out.println();
                        }
                    }
                    if (!foundPendingInternships) {
                        System.out.println("No pending internship opportunities.");
                    }
                    loggedIn.updateInternshipCSV(internships);
                    break;
                    
                case 4:
                    System.out.println("\n=== Student Withdrawal Requests ===");
                    boolean foundWithdrawals = false;
                    for (int i = 0; i < studentCount; i++) {
                        if (students[i] != null && students[i].getWithdrawalCount() > 0) {
                            System.out.println("\nStudent: " + students[i].getName() + " (ID: " + students[i].getId() + ")");
                            String[] withdrawals = students[i].getWithdrawalRequests();
                            for (int j = 0; j < students[i].getWithdrawalCount(); j++) {
                                if (withdrawals[j] != null) {
                                    foundWithdrawals = true;
                                    System.out.println("  Withdrawal Request: " + withdrawals[j]);
                                    System.out.print("  Approve this withdrawal? (Y/N): ");
                                    String ans = sc.nextLine();
                                    if (ans.equalsIgnoreCase("Y")) {
                                        loggedIn.approveWithdrawal(students[i], withdrawals[j]);
                                    } else {
                                        loggedIn.rejectWithdrawal(students[i], withdrawals[j]);
                                    }
                                }
                            }
                        }
                    }
                    if (!foundWithdrawals) {
                        System.out.println("No pending withdrawal requests.");
                    }
                    break;
                    
                case 5:
                    System.out.println("\n=== Generate Report ===");
                    System.out.println("Filter by:");
                    System.out.println("1. Status");
                    System.out.println("2. Major");
                    System.out.println("3. Level");
                    System.out.println("4. Company");
                    System.out.println("5. All (no filter)");
                    System.out.print("Select filter: ");
                    int filterChoice = sc.nextInt();
                    sc.nextLine();
                    
                    String filterType = "";
                    String filterValue = "";
                    
                    switch (filterChoice) {
                        case 1:
                            filterType = "status";
                            System.out.print("Enter status (Pending/Approved/Rejected/Filled): ");
                            filterValue = sc.nextLine();
                            break;
                        case 2:
                            filterType = "major";
                            System.out.print("Enter major: ");
                            filterValue = sc.nextLine();
                            break;
                        case 3:
                            filterType = "level";
                            System.out.print("Enter level (Basic/Intermediate/Advanced): ");
                            filterValue = sc.nextLine();
                            break;
                        case 4:
                            filterType = "company";
                            System.out.print("Enter company name: ");
                            filterValue = sc.nextLine();
                            break;
                        case 5:
                            filterType = "all";
                            filterValue = "";
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            continue;
                    }
                    
                    loggedIn.generateReport(internships, filterType, filterValue);
                    break;
                    
                case 6:
                    System.out.print("Enter old password: ");
                    String oldPw = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPw = sc.nextLine();
                    loggedIn.changePassword(oldPw, newPw);
                    break;
                    
                case 7:
                    loggedIn.logout();
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }
}
