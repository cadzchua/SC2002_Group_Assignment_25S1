package InternshipSystem;

public class Internship {
    private String title;
    private String description;
    private String level;
    private String preferredMajor;
    private String openingDate;
    private String closingDate;
    private String status; // "Pending", "Approved", "Rejected", "Filled"
    private String companyName;
    private String companyRep;
    private int slots;
    private int filledSlots = 0;
    private boolean visible = false; // visibility toggle
    private Student[] applicants = new Student[50];
    private int applicantCount = 0;

    public Internship(String title, String description, String level, String preferredMajor, 
                      String openingDate, String closingDate, String status, String companyName, 
                      String companyRep, int slots) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.preferredMajor = preferredMajor;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.status = status;
        this.companyName = companyName;
        this.companyRep = companyRep;
        this.slots = Math.min(slots, 10); // max 10 slots
    }

    public void showInfo() {
        System.out.println("\n--- Internship Details ---");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Level: " + level);
        System.out.println("Preferred Major: " + preferredMajor);
        System.out.println("Company: " + companyName);
        System.out.println("Company Rep: " + companyRep);
        System.out.println("Status: " + status);
        System.out.println("Slots: " + filledSlots + "/" + slots);
        System.out.println("Opening Date: " + openingDate);
        System.out.println("Closing Date: " + closingDate);
        System.out.println("Visible: " + (visible ? "Yes" : "No"));
    }

    public void addApplicant(Student student) {
        if (applicantCount < applicants.length) {
            applicants[applicantCount++] = student;
        }
    }

    public void viewApplicants() {
        System.out.println("\n=== Applicants for: " + title + " ===");
        if (applicantCount == 0) {
            System.out.println("No applicants yet.");
            return;
        }
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i] != null) {
                System.out.println((i+1) + ". " + applicants[i].getName() + 
                                   " (ID: " + applicants[i].getId() + 
                                   ", Major: " + applicants[i].getMajor() + 
                                   ", Year: " + applicants[i].getYear() + ")");
            }
        }
    }

    public void approveApplication(String studentId) {
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i] != null && applicants[i].getId().equals(studentId)) {
                applicants[i].updateApplicationStatus(title, "Successful");
                System.out.println("Application approved for student: " + applicants[i].getName());
                return;
            }
        }
        System.out.println("Student not found in applicants.");
    }

    public void rejectApplication(String studentId) {
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i] != null && applicants[i].getId().equals(studentId)) {
                applicants[i].updateApplicationStatus(title, "Unsuccessful");
                System.out.println("Application rejected for student: " + applicants[i].getName());
                return;
            }
        }
        System.out.println("Student not found in applicants.");
    }

    public void confirmPlacement() {
        filledSlots++;
        if (filledSlots >= slots) {
            status = "Filled";
            System.out.println("All slots filled. Internship status set to 'Filled'.");
        }
    }

    public void toggleVisibility() {
        visible = !visible;
        System.out.println("Visibility toggled to: " + (visible ? "ON" : "OFF"));
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisibility() {
        return visible;
    }

    public boolean isVisible() {
        return visible && status.equals("Approved");
    }

    public boolean isEligibleForStudent(Student student) {
        // Check if student's major matches
        if (!preferredMajor.equalsIgnoreCase(student.getMajor())) {
            return false;
        }
        // Check year restrictions for Basic level
        if (student.getYear() < 3 && !level.equalsIgnoreCase("Basic")) {
            return false;
        }
        return true;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public String getPreferredMajor() {
        return preferredMajor;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyRep() {
        return companyRep;
    }

    public int getSlots() {
        return slots;
    }

    public int getFilledSlots() {
        return filledSlots;
    }

    public Student[] getApplicants() {
        return applicants;
    }

    public int getApplicantCount() {
        return applicantCount;
    }
}


