package InternshipSystem;

public class InternshipEntity {
    private String title;
    private String description;
    private String level;
    private String preferredMajor;
    private String openingDate;
    private String closingDate;
    private String status;
    private String companyName;
    private String companyRep;
    private int slots;
    private int filledSlots = 0;
    private boolean visible = false;
    private StudentEntity[] applicants = new StudentEntity[50];
    private int applicantCount = 0;

    public InternshipEntity(String title, String description, String level, String preferredMajor, 
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
        this.slots = Math.min(slots, 10);
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

    public void addApplicant(StudentEntity student) {
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

    public void releaseSlot() {
        if (filledSlots > 0) {
            filledSlots--;
            if (status.equals("Filled") && filledSlots < slots) {
                status = "Approved";
                System.out.println("Slot released. Internship status set back to 'Approved'.");
            }
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

    public boolean isEligibleForStudent(StudentEntity student) {
        if (!preferredMajor.equalsIgnoreCase(student.getMajor())) {
            return false;
        }
        if (student.getYear() < 3 && !level.equalsIgnoreCase("Basic")) {
            return false;
        }
        return true;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setSlots(int slots) {
        this.slots = Math.min(Math.max(1, slots), 10); 
    }

    public int getFilledSlots() {
        return filledSlots;
    }

    public StudentEntity[] getApplicants() {
        return applicants;
    }

    public int getApplicantCount() {
        return applicantCount;
    }
}
