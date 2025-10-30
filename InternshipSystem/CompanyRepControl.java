package InternshipSystem;

import java.util.Scanner;

public class CompanyRepControl {

    private CompanyRepEntity[] reps;
    private int repCount;
    private InternshipEntity[] internships;

    public CompanyRepControl(CompanyRepEntity[] reps, int repCount, InternshipEntity[] internships) {
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
            reps[repCount++] = new CompanyRepEntity(id, name, companyName, department, position, email);

            String csvLine = id + "," + name + "," + companyName + "," + department + "," + position + "," + email + ",Pending";
            CSVReaderControl.appendCSV("sample_company_representative_list.csv", csvLine);
            
            System.out.println("Registration submitted! Awaiting approval from Career Center Staff.");
        } else {
            System.out.println("Maximum number of company representatives reached.");
        }
    }

    public void showCompanyRepCLI(Scanner sc) {
        int option = -1;
        while (option < 1 || option > 3) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select option: ");
            try {
                option = sc.nextInt();
                sc.nextLine();
                if (option < 1 || option > 3) {
                    System.out.println("Invalid option! Please select 1, 2, or 3.");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid option! Please select 1, 2, or 3.");
                option = -1;
            }
        }

        if (option == 3) {
            System.out.println("Returning to Main Menu...");
            return;
        }

        if (option == 2) {
            registerCompanyRep(sc);
            return;
        }

        System.out.print("Enter your ID: ");
        String id = sc.nextLine();
        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        CompanyRepEntity loggedIn = null;
        boolean idFound = false;
        for (int i = 0; i < repCount; i++) {
            if (reps[i].getId().equals(id)) {
                idFound = true;
                if (reps[i].login(id, pw)) {
                    if (!reps[i].isApproved()) {
                        System.out.println("Your account is pending approval from Career Center Staff.");
                        return;
                    }
                    loggedIn = reps[i];
                }
                break;
            }
        }

        if (loggedIn == null) {
            if (!idFound) {
                System.out.println("Login failed! Invalid ID - No company representative found with this ID.");
            } else {
                System.out.println("Login failed! Incorrect password.");
            }
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
            System.out.println("7. Edit Internship");
            System.out.println("8. Delete Internship");
            System.out.println("9. Change Password");
            System.out.println("10. Logout");
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
                    if (!InputValidatorControl.isNotEmpty(title)) {
                        System.out.println("Error: Title cannot be empty!");
                        break;
                    }
                    
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    if (!InputValidatorControl.isNotEmpty(desc)) {
                        System.out.println("Error: Description cannot be empty!");
                        break;
                    }
                    
                    String level = "";
                    while (true) {
                        System.out.print("Level (Basic/Intermediate/Advanced): ");
                        level = sc.nextLine();
                        if (InputValidatorControl.isValidLevel(level)) {
                            level = InputValidatorControl.normalizeLevel(level);
                            break;
                        } else {
                            System.out.println(InputValidatorControl.getLevelError());
                            System.out.print("Try again? (Y/N): ");
                            String retry = sc.nextLine();
                            if (!retry.equalsIgnoreCase("Y")) {
                                System.out.println("Internship creation cancelled.");
                                break;
                            }
                        }
                    }
                    if (!InputValidatorControl.isValidLevel(level)) {
                        break;
                    }
                    
                    String major = "";
                    while (true) {
                        System.out.println("\nValid Majors:");
                        System.out.println(InputValidatorControl.getValidMajorsList());
                        System.out.print("Preferred Major: ");
                        major = sc.nextLine();
                        if (InputValidatorControl.isValidMajor(major)) {
                            major = InputValidatorControl.normalizeMajor(major);
                            break;
                        } else {
                            System.out.println(InputValidatorControl.getMajorError());
                            System.out.print("Try again? (Y/N): ");
                            String retry = sc.nextLine();
                            if (!retry.equalsIgnoreCase("Y")) {
                                System.out.println("Internship creation cancelled.");
                                break;
                            }
                        }
                    }
                    if (!InputValidatorControl.isValidMajor(major)) {
                        break;
                    }
                    
                    String openingDate = "";
                    while (true) {
                        System.out.print("Opening Date (YYYY-MM-DD): ");
                        openingDate = sc.nextLine();
                        if (InputValidatorControl.isValidDate(openingDate)) {
                            break;
                        } else {
                            System.out.println(InputValidatorControl.getDateFormatError());
                            System.out.print("Try again? (Y/N): ");
                            String retry = sc.nextLine();
                            if (!retry.equalsIgnoreCase("Y")) {
                                System.out.println("Internship creation cancelled.");
                                break;
                            }
                        }
                    }
                    if (!InputValidatorControl.isValidDate(openingDate)) {
                        break;
                    }
                    
                    String closingDate = "";
                    while (true) {
                        System.out.print("Closing Date (YYYY-MM-DD): ");
                        closingDate = sc.nextLine();
                        if (!InputValidatorControl.isValidDate(closingDate)) {
                            System.out.println(InputValidatorControl.getDateFormatError());
                        } else if (!InputValidatorControl.isClosingAfterOpening(openingDate, closingDate)) {
                            System.out.println("Error: Closing date must be after opening date!");
                        } else {
                            break;
                        }
                        System.out.print("Try again? (Y/N): ");
                        String retry = sc.nextLine();
                        if (!retry.equalsIgnoreCase("Y")) {
                            System.out.println("Internship creation cancelled.");
                            break;
                        }
                    }
                    if (!InputValidatorControl.isValidDate(closingDate) || 
                        !InputValidatorControl.isClosingAfterOpening(openingDate, closingDate)) {
                        break;
                    }
                    
                    int slots = 0;
                    while (true) {
                        System.out.print("Number of slots (1-10): ");
                        try {
                            slots = sc.nextInt();
                            sc.nextLine();
                            if (InputValidatorControl.isValidSlots(slots)) {
                                break;
                            } else {
                                System.out.println("Error: Slots must be between 1 and 10!");
                            }
                        } catch (Exception e) {
                            sc.nextLine();
                            System.out.println("Error: Please enter a valid number!");
                        }
                        System.out.print("Try again? (Y/N): ");
                        String retry = sc.nextLine();
                        if (!retry.equalsIgnoreCase("Y")) {
                            System.out.println("Internship creation cancelled.");
                            slots = 0;
                            break;
                        }
                    }
                    if (slots == 0) {
                        break;
                    }

                    loggedIn.createInternship(title, desc, level, major, openingDate, closingDate, slots);

                    for (int i = 0; i < internships.length; i++) {
                        if (internships[i] == null) {
                            InternshipEntity[] repInternships = loggedIn.getInternships();
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
                    loggedIn.viewInternshipsNumbered();
                    break;
                    
                case 4:
                    InternshipEntity[] repInternships = loggedIn.getInternships();
                    int repInternshipCount = loggedIn.getInternshipCount();
                    
                    if (repInternshipCount == 0) {
                        System.out.println("You haven't created any internships yet.");
                        break;
                    }
                    
                    System.out.println("\n=== Your Internships ===");
                    for (int i = 0; i < repInternshipCount; i++) {
                        if (repInternships[i] != null) {
                            System.out.println((i + 1) + ". " + repInternships[i].getTitle() + 
                                             " (" + repInternships[i].getStatus() + ")");
                        }
                    }
                    System.out.print("Select internship number to view applications (or 0 to cancel): ");
                    int viewChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (viewChoice > 0 && viewChoice <= repInternshipCount) {
                        repInternships[viewChoice - 1].viewApplicants();
                    } else if (viewChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 5:
                    InternshipEntity[] appInternships = loggedIn.getInternships();
                    int appInternshipCount = loggedIn.getInternshipCount();
                    
                    if (appInternshipCount == 0) {
                        System.out.println("You haven't created any internships yet.");
                        break;
                    }
                    
                    System.out.println("\n=== Your Internships ===");
                    for (int i = 0; i < appInternshipCount; i++) {
                        if (appInternships[i] != null) {
                            System.out.println((i + 1) + ". " + appInternships[i].getTitle() + 
                                             " - Applicants: " + appInternships[i].getApplicantCount());
                        }
                    }
                    System.out.print("Select internship number (or 0 to cancel): ");
                    int appChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (appChoice > 0 && appChoice <= appInternshipCount) {
                        InternshipEntity selectedInternship = appInternships[appChoice - 1];
                        
                        selectedInternship.viewApplicants();
                        
                        if (selectedInternship.getApplicantCount() > 0) {
                            System.out.print("\nSelect applicant number to process (or 0 to cancel): ");
                            int studentChoice = sc.nextInt();
                            sc.nextLine();
                            
                            if (studentChoice > 0 && studentChoice <= selectedInternship.getApplicantCount()) {
                                StudentEntity[] applicants = selectedInternship.getApplicants();
                                String studentId = applicants[studentChoice - 1].getId();
                                
                                System.out.print("Approve or Reject? (A/R): ");
                                String decision = sc.nextLine();
                                
                                if (decision.equalsIgnoreCase("A")) {
                                    selectedInternship.approveApplication(studentId);
                                } else if (decision.equalsIgnoreCase("R")) {
                                    selectedInternship.rejectApplication(studentId);
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            }
                        }
                    } else if (appChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 6:
                    InternshipEntity[] toggleInternships = loggedIn.getInternships();
                    int toggleInternshipCount = loggedIn.getInternshipCount();
                    
                    if (toggleInternshipCount == 0) {
                        System.out.println("You haven't created any internships yet.");
                        break;
                    }
                    
                    System.out.println("\n=== Your Internships ===");
                    for (int i = 0; i < toggleInternshipCount; i++) {
                        if (toggleInternships[i] != null) {
                            String visibility = toggleInternships[i].getVisibility() ? "Visible" : "Hidden";
                            System.out.println((i + 1) + ". " + toggleInternships[i].getTitle() + 
                                             " (" + visibility + ")");
                        }
                    }
                    System.out.print("Select internship number to toggle visibility (or 0 to cancel): ");
                    int toggleChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (toggleChoice > 0 && toggleChoice <= toggleInternshipCount) {
                        toggleInternships[toggleChoice - 1].toggleVisibility();
                    } else if (toggleChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 7:
                    InternshipEntity[] editInternships = loggedIn.getInternships();
                    int editInternshipCount = loggedIn.getInternshipCount();
                    
                    if (editInternshipCount == 0) {
                        System.out.println("You haven't created any internships yet.");
                        break;
                    }
                    
                    System.out.println("\n=== Your Internships ===");
                    for (int i = 0; i < editInternshipCount; i++) {
                        if (editInternships[i] != null) {
                            System.out.println((i + 1) + ". " + editInternships[i].getTitle() + 
                                             " (Status: " + editInternships[i].getStatus() + ")");
                        }
                    }
                    System.out.print("Select internship number to edit (or 0 to cancel): ");
                    int editChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (editChoice > 0 && editChoice <= editInternshipCount) {
                        InternshipEntity toEdit = editInternships[editChoice - 1];
                        if (toEdit.getStatus().equals("Approved")) {
                            System.out.println("Cannot edit an approved internship. Changes are restricted after approval.");
                            break;
                        }
                        
                        System.out.println("\nEditing: " + toEdit.getTitle());
                        System.out.println("Leave blank to keep current value.");
                        
                        System.out.print("New Description (current: " + toEdit.getDescription() + "): ");
                        String newDesc = sc.nextLine();
                        if (!newDesc.trim().isEmpty()) {
                            loggedIn.editInternshipDescription(toEdit.getTitle(), newDesc);
                        }
                        
                        System.out.print("New Slots (current: " + toEdit.getSlots() + ", 1-10): ");
                        String newSlotsStr = sc.nextLine();
                        if (!newSlotsStr.trim().isEmpty()) {
                            try {
                                int newSlots = Integer.parseInt(newSlotsStr);
                                if (InputValidatorControl.isValidSlots(newSlots)) {
                                    loggedIn.editInternshipSlots(toEdit.getTitle(), newSlots);
                                } else {
                                    System.out.println("Invalid slots. Must be between 1 and 10.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format.");
                            }
                        }
                        
                        System.out.println("Internship updated successfully.");
                        
                        int totalInternships = 0;
                        for (InternshipEntity internship : internships) {
                            if (internship != null) totalInternships++;
                        }
                        CSVReaderControl.updateInternshipCSV("sample_internship_list.csv", internships, totalInternships);
                    } else if (editChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 8:
                    InternshipEntity[] delInternships = loggedIn.getInternships();
                    int delInternshipCount = loggedIn.getInternshipCount();
                    
                    if (delInternshipCount == 0) {
                        System.out.println("You haven't created any internships yet.");
                        break;
                    }
                    
                    System.out.println("\n=== Your Internships ===");
                    for (int i = 0; i < delInternshipCount; i++) {
                        if (delInternships[i] != null) {
                            System.out.println((i + 1) + ". " + delInternships[i].getTitle() + 
                                             " (Status: " + delInternships[i].getStatus() + ")");
                        }
                    }
                    System.out.print("Select internship number to delete (or 0 to cancel): ");
                    int delChoice = sc.nextInt();
                    sc.nextLine();
                    
                    if (delChoice > 0 && delChoice <= delInternshipCount) {
                        InternshipEntity toDelete = delInternships[delChoice - 1];
                        System.out.print("Are you sure you want to delete '" + toDelete.getTitle() + "'? (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            String titleToDelete = toDelete.getTitle();
                            loggedIn.deleteInternship(titleToDelete);

                            for (int i = 0; i < internships.length; i++) {
                                if (internships[i] != null && internships[i].getTitle().equals(titleToDelete)) {
                                    for (int j = i; j < internships.length - 1; j++) {
                                        internships[j] = internships[j + 1];
                                    }
                                    internships[internships.length - 1] = null;
                                    break;
                                }
                            }
                            
                            int totalInternships = 0;
                            for (InternshipEntity internship : internships) {
                                if (internship != null) totalInternships++;
                            }
                            CSVReaderControl.updateInternshipCSV("sample_internship_list.csv", internships, totalInternships);
                            
                            System.out.println("Internship deleted successfully.");
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    } else if (delChoice != 0) {
                        System.out.println("Invalid selection.");
                    }
                    break;
                    
                case 9:
                    System.out.print("Enter old password: ");
                    String oldPw = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPw = sc.nextLine();
                    if (loggedIn.changePassword(oldPw, newPw)) {
                        choice = 10; 
                    }
                    break;
                    
                case 10:
                    loggedIn.logout();
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 10);
    }

    public CompanyRepEntity[] getReps() {
        return reps;
    }

    public int getRepCount() {
        return repCount;
    }
}
