package InternshipSystem;

public class CompanyRep extends User {
    private String companyName;
    private String department;
    private String position;
    private boolean approved = false;
    private Internship[] internships = new Internship[5];
    private int internshipCount = 0;

    public CompanyRep(String id, String name, String companyName, String department, String position, String email) {
        super(id, name, email);
        this.companyName = companyName;
        this.department = department;
        this.position = position;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean val) {
        approved = val;
    }

    public void createInternship(String title, String desc, String level, String major, 
                                 String openingDate, String closingDate, int slots) {
        if (internshipCount >= 5) {
            System.out.println("Maximum 5 internships allowed per company representative.");
            return;
        }
        if (slots > 10) {
            slots = 10;
            System.out.println("Maximum slots per internship is 10. Adjusted to 10.");
        }
        Internship newInternship = new Internship(title, desc, level, major, openingDate, 
                                                  closingDate, "Pending", companyName, 
                                                  this.name, slots);
        // Set visibility to ON by default
        newInternship.setVisible(true);
        internships[internshipCount++] = newInternship;
        
        // Save to CSV file
        // CSV format: Title,Description,Level,PreferredMajor,OpeningDate,ClosingDate,Status,CompanyName,CompanyRep,Slots,Visible
        String csvLine = title + "," + desc + "," + level + "," + major + "," + 
                        openingDate + "," + closingDate + ",Pending," + companyName + "," + 
                        this.name + "," + slots + ",true";
        CSVReader.appendCSV("sample_internship_list.csv", csvLine);
        
        System.out.println("Internship \"" + title + "\" created and pending approval from Career Center Staff.");
    }

    public void viewInternships() {
        System.out.println("\n=== Your Internships ===");
        if (internshipCount == 0) {
            System.out.println("No internships created yet.");
            return;
        }
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null) {
                internships[i].showInfo();
                System.out.println("------------------------");
            }
        }
    }

    public void viewInternshipsNumbered() {
        System.out.println("\n=== Your Internships ===");
        if (internshipCount == 0) {
            System.out.println("No internships created yet.");
            return;
        }
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null) {
                System.out.println("\n[" + (i + 1) + "]");
                internships[i].showInfo();
                System.out.println("------------------------");
            }
        }
    }

    public void viewApplicationsForInternship(String title) {
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null && internships[i].getTitle().equals(title)) {
                internships[i].viewApplicants();
                return;
            }
        }
        System.out.println("Internship not found.");
    }

    public void approveApplication(String internshipTitle, String studentId) {
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null && internships[i].getTitle().equals(internshipTitle)) {
                internships[i].approveApplication(studentId);
                return;
            }
        }
        System.out.println("Internship not found.");
    }

    public void rejectApplication(String internshipTitle, String studentId) {
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null && internships[i].getTitle().equals(internshipTitle)) {
                internships[i].rejectApplication(studentId);
                return;
            }
        }
        System.out.println("Internship not found.");
    }

    public void toggleInternshipVisibility(String title) {
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null && internships[i].getTitle().equals(title)) {
                internships[i].toggleVisibility();
                return;
            }
        }
        System.out.println("Internship not found.");
    }

    public Internship[] getInternships() {
        return internships;
    }

    public int getInternshipCount() {
        return internshipCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Company: " + companyName);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Approved: " + (approved ? "Yes" : "No"));
    }
}
