package InternshipSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student[] students = CSVLoader.loadStudents();
        int studentCount = CSVLoader.getStudentCount();
        CareerCentreStaff[] staff = CSVLoader.loadStaff();
        int staffCount = CSVLoader.getStaffCount();

        CompanyRep[] reps = CSVLoader.loadCompanyReps();
        int repCount = CSVLoader.getCompanyRepCount();
        
        Internship[] internships = CSVLoader.loadInternships();
        int internshipCount = CSVLoader.getInternshipCount();

        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null) {
                String companyRepName = internships[i].getCompanyRep();
                for (int j = 0; j < repCount; j++) {
                    if (reps[j] != null && reps[j].getName().equals(companyRepName)) {
                        reps[j].addLoadedInternship(internships[i]);
                        break;
                    }
                }
            }
        }

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
                    repCount = cm.getRepCount();
                    reps = cm.getReps();
                    stm = new StaffManager(staff, staffCount, reps, repCount, internships, students, studentCount);
                    break;
                case 3:
                    stm.showStaffCLI(sc);
                    break;
                case 4:
                    System.out.println("Saving data...");
                    
                    int totalInternships = internshipCount;
                    for (int i = 0; i < repCount; i++) {
                        if (reps[i] != null) {
                            Internship[] repInternships = reps[i].getInternships();
                            for (int j = 0; j < reps[i].getInternshipCount(); j++) {
                                if (repInternships[j] != null) {
                                    boolean found = false;
                                    for (int k = 0; k < internshipCount; k++) {
                                        if (internships[k] != null && 
                                            internships[k].getTitle().equals(repInternships[j].getTitle())) {
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found && totalInternships < internships.length) {
                                        internships[totalInternships++] = repInternships[j];
                                    }
                                }
                            }
                        }
                    }
                    
                    CSVReader.updateInternshipCSV("sample_internship_list.csv", internships, totalInternships);
                    System.out.println("Thank you for using the Internship Placement System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
        
        sc.close();
    }
}
