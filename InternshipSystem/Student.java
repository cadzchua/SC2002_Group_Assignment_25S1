package InternshipSystem;


public class Student extends User {
    private String major;
    private int year;
    private String[] appliedInternships = new String[3];
    private String[] applicationStatus = new String[3]; // "Pending", "Successful", "Unsuccessful"
    private int appCount = 0;
    private String acceptedInternship = null;
    private String[] withdrawalRequests = new String[3]; // Track withdrawal requests
    private int withdrawalCount = 0;

    public Student(String id, String name, String major, int year, String email) {
        super(id, name, email);
        this.major = major;
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public boolean canApply() {
        return appCount < 3 && acceptedInternship == null;
    }

    public void applyInternship(Internship internship) {
        if (appCount >= 3) {
            System.out.println("You can only apply for 3 internships at once.");
            return;
        }

        if (acceptedInternship != null) {
            System.out.println("You have already accepted an internship placement.");
            return;
        }

        // Check year restrictions
        if (year < 3 && !internship.getLevel().equalsIgnoreCase("Basic")) {
            System.out.println("Year 1-2 students can only apply for Basic-level internships.");
            return;
        }

        // Check if internship is available
        if (!internship.getStatus().equals("Approved")) {
            System.out.println("This internship is not available for applications.");
            return;
        }

        if (internship.getStatus().equals("Filled")) {
            System.out.println("This internship is already filled.");
            return;
        }

        appliedInternships[appCount] = internship.getTitle();
        applicationStatus[appCount] = "Pending";
        appCount++;
        internship.addApplicant(this);
        System.out.println("Applied to: " + internship.getTitle());
    }

    public void viewApplications() {
        System.out.println("\n=== Your Internship Applications ===");
        if (appCount == 0) {
            System.out.println("No applications yet.");
            return;
        }
        for (int i = 0; i < appCount; i++) {
            System.out.println((i+1) + ". " + appliedInternships[i] + " - Status: " + applicationStatus[i]);
        }
    }

    public void updateApplicationStatus(String internshipTitle, String status) {
        for (int i = 0; i < appCount; i++) {
            if (appliedInternships[i].equals(internshipTitle)) {
                applicationStatus[i] = status;
                System.out.println("Application status updated to: " + status);
                return;
            }
        }
    }

    public void acceptPlacement(String internshipTitle) {
        // Check if the application is successful
        for (int i = 0; i < appCount; i++) {
            if (appliedInternships[i].equals(internshipTitle) && applicationStatus[i].equals("Successful")) {
                acceptedInternship = internshipTitle;
                System.out.println("Internship placement accepted: " + internshipTitle);
                
                // Withdraw all other applications
                for (int j = 0; j < appCount; j++) {
                    if (j != i && !applicationStatus[j].equals("Unsuccessful")) {
                        applicationStatus[j] = "Withdrawn";
                    }
                }
                return;
            }
        }
        System.out.println("Cannot accept this placement. Application must be 'Successful'.");
    }

    public void requestWithdrawal(String internshipTitle) {
        boolean found = false;
        for (int i = 0; i < appCount; i++) {
            if (appliedInternships[i].equals(internshipTitle)) {
                found = true;
                break;
            }
        }

        if (!found && (acceptedInternship == null || !acceptedInternship.equals(internshipTitle))) {
            System.out.println("You have not applied for this internship.");
            return;
        }

        // Add to withdrawal requests
        if (withdrawalCount < 3) {
            withdrawalRequests[withdrawalCount++] = internshipTitle;
            System.out.println("Withdrawal request submitted for: " + internshipTitle);
            System.out.println("Pending approval from Career Center Staff.");
        }
    }

    public String[] getWithdrawalRequests() {
        return withdrawalRequests;
    }

    public int getWithdrawalCount() {
        return withdrawalCount;
    }

    public void approveWithdrawal(String internshipTitle) {
        // Remove from applied internships or accepted placement
        if (acceptedInternship != null && acceptedInternship.equals(internshipTitle)) {
            acceptedInternship = null;
            System.out.println("Placement withdrawn: " + internshipTitle);
        }

        for (int i = 0; i < appCount; i++) {
            if (appliedInternships[i].equals(internshipTitle)) {
                applicationStatus[i] = "Withdrawn";
                System.out.println("Application withdrawn: " + internshipTitle);
            }
        }

        // Remove from withdrawal requests
        for (int i = 0; i < withdrawalCount; i++) {
            if (withdrawalRequests[i] != null && withdrawalRequests[i].equals(internshipTitle)) {
                withdrawalRequests[i] = null;
                break;
            }
        }
    }

    public String[] getAppliedInternships() {
        return appliedInternships;
    }

    public String[] getApplicationStatus() {
        return applicationStatus;
    }

    public int getAppCount() {
        return appCount;
    }

    public String getAcceptedInternship() {
        return acceptedInternship;
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        if (acceptedInternship != null) {
            System.out.println("Accepted Internship: " + acceptedInternship);
        }
    }
}
