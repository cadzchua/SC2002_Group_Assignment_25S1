package InternshipSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load CSV data for students and staff
        Student[] students = CSVLoader.loadStudents();
        int studentCount = CSVLoader.getStudentCount();
        CareerCentreStaff[] staff = CSVLoader.loadStaff();
        int staffCount = CSVLoader.getStaffCount();

        // Load company representatives from CSV
        CompanyRep[] reps = CSVLoader.loadCompanyReps();
        int repCount = CSVLoader.getCompanyRepCount();
        
        // Initialize internships array
        Internship[] internships = new Internship[100];

        // Create managers
        StudentManager sm = new StudentManager(students, studentCount, internships);
        CompanyRepManager cm = new CompanyRepManager(reps, repCount, internships);
        StaffManager stm = new StaffManager(staff, staffCount, reps, repCount, internships, students, studentCount);

        System.out.println("=== Welcome to Internship Placement System ===");

        int choice;
        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Login");
            System.out.println("2. Company Representative Login/Register");
            System.out.println("3. Career Center Staff Login");
            System.out.println("4. Exit");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    sm.showStudentCLI(sc);
                    break;
                case 2:
                    cm.showCompanyRepCLI(sc);
                    // Update repCount after registration
                    repCount = cm.getRepCount();
                    reps = cm.getReps();
                    // Update StaffManager with new repCount
                    stm = new StaffManager(staff, staffCount, reps, repCount, internships, students, studentCount);
                    break;
                case 3:
                    stm.showStaffCLI(sc);
                    break;
                case 4:
                    System.out.println("Thank you for using the Internship Placement System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
        
        sc.close();
    }
}
