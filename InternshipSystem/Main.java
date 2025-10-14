package InternshipSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load CSV data (students & staff) as before...
        Student[] students = CSVLoader.loadStudents(); // implement CSVLoader helper
        int studentCount = CSVLoader.getStudentCount();
        CareerCentreStaff[] staff = CSVLoader.loadStaff();
        int staffCount = CSVLoader.getStaffCount();

        CompanyRep[] reps = new CompanyRep[50];
        int repCount = 0;
        Internship[] internships = new Internship[50];

        StudentManager sm = new StudentManager(students, studentCount, internships);
        CompanyRepManager cm = new CompanyRepManager(reps, repCount, internships);
        StaffManager stm = new StaffManager(staff, staffCount, reps, repCount);

        int choice;
        do {
            System.out.println("\n=== Internship Placement System ===");
            System.out.println("1. Student Login");
            System.out.println("2. Company Rep Login");
            System.out.println("3. Staff Login");
            System.out.println("4. Exit");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: sm.showStudentCLI(sc); break;
                case 2: cm.showCompanyRepCLI(sc); break;
                case 3: stm.showStaffCLI(sc); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
