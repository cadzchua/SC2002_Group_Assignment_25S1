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
                    // Title validation
                    System.out.print("Internship Title: ");
                    String title = sc.nextLine();
                    if (!InputValidator.isNotEmpty(title)) {
                        System.out.println("Error: Title cannot be empty!");
                        break;
                    }
                    
                    // Description validation
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    if (!InputValidator.isNotEmpty(desc)) {
                        System.out.println("Error: Description cannot be empty!");
                        break;
                    }
                    
                    // Level validation with retry
                    String level = "";
                    while (true) {
                        System.out.print("Level (Basic/Intermediate/Advanced): ");
                        level = sc.nextLine();
                        if (InputValidator.isValidLevel(level)) {
                            level = InputValidator.normalizeLevel(level);
                            break;
                        } else {
                            System.out.println(InputValidator.getLevelError());
                            System.out.print("Try again? (Y/N): ");
                            String retry = sc.nextLine();
                            if (!retry.equalsIgnoreCase("Y")) {
                                System.out.println("Internship creation cancelled.");
                                break;
                            }
                        }
                    }
                    if (!InputValidator.isValidLevel(level)) {
                        break; // User chose not to retry
                    }
                    
                    // Major validation with list display
                    String major = "";
                    while (true) {
                        System.out.println("\nValid Majors:");
                        System.out.println(InputValidator.getValidMajorsList());
                        System.out.print("Preferred Major: ");
                        major = sc.nextLine();
                        if (InputValidator.isValidMajor(major)) {
                            major = InputValidator.normalizeMajor(major);
                            break;
                        } else {
                            System.out.println(InputValidator.getMajorError());
                            System.out.print("Try again? (Y/N): ");
                            String retry = sc.nextLine();
                            if (!retry.equalsIgnoreCase("Y")) {
                                System.out.println("Internship creation cancelled.");
                                break;
                            }
                        }
                    }
                    if (!InputValidator.isValidMajor(major)) {
                        break; // User chose not to retry
                    }
                    
                    // Opening date validation
                    String openingDate = "";
                    while (true) {
                        System.out.print("Opening Date (YYYY-MM-DD): ");
                        openingDate = sc.nextLine();
                        if (InputValidator.isValidDate(openingDate)) {
                            break;
                        } else {
                            System.out.println(InputValidator.getDateFormatError());
                            System.out.print("Try again? (Y/N): ");
                            String retry = sc.nextLine();
                            if (!retry.equalsIgnoreCase("Y")) {
                                System.out.println("Internship creation cancelled.");
                                break;
                            }
                        }
                    }
                    if (!InputValidator.isValidDate(openingDate)) {
                        break; // User chose not to retry
                    }
                    
                    // Closing date validation
                    String closingDate = "";
                    while (true) {
                        System.out.print("Closing Date (YYYY-MM-DD): ");
                        closingDate = sc.nextLine();
                        if (!InputValidator.isValidDate(closingDate)) {
                            System.out.println(InputValidator.getDateFormatError());
                        } else if (!InputValidator.isClosingAfterOpening(openingDate, closingDate)) {
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
                    if (!InputValidator.isValidDate(closingDate) || 
                        !InputValidator.isClosingAfterOpening(openingDate, closingDate)) {
                        break; // User chose not to retry
                    }
                    
                    // Slots validation
                    int slots = 0;
                    while (true) {
                        System.out.print("Number of slots (1-10): ");
                        try {
                            slots = sc.nextInt();
                            sc.nextLine();
                            if (InputValidator.isValidSlots(slots)) {
                                break;
                            } else {
                                System.out.println("Error: Slots must be between 1 and 10!");
                            }
                        } catch (Exception e) {
                            sc.nextLine(); // clear buffer
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
                        break; // User chose not to retry
                    }

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
                    loggedIn.viewInternshipsNumbered();
                    break;
                    
                case 4:
                    // Show numbered list of internships
                    Internship[] repInternships = loggedIn.getInternships();
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
                    // Show numbered list of internships
                    Internship[] appInternships = loggedIn.getInternships();
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
                        Internship selectedInternship = appInternships[appChoice - 1];
                        
                        // Show applicants
                        selectedInternship.viewApplicants();
                        
                        if (selectedInternship.getApplicantCount() > 0) {
                            System.out.print("\nSelect applicant number to process (or 0 to cancel): ");
                            int studentChoice = sc.nextInt();
                            sc.nextLine();
                            
                            if (studentChoice > 0 && studentChoice <= selectedInternship.getApplicantCount()) {
                                Student[] applicants = selectedInternship.getApplicants();
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
                    // Show numbered list of internships
                    Internship[] toggleInternships = loggedIn.getInternships();
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

