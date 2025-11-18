package InternshipSystem;


/**
 * Represents a student in the internship placement system.
 * Extends the UserEntity class to include student-specific attributes and behaviors.
 */
public class StudentEntity extends UserEntity {
    /**
     * The major of the student.
     */
    private String major;

    /**
     * The year of study of the student.
     */
    private int year;

    /**
     * Array to store the titles of internships the student has applied for.
     */
    private String[] appliedInternships = new String[3];

    /**
     * Array to store the application statuses for the internships.
     */
    private String[] applicationStatus = new String[3];

    /**
     * The count of internships the student has applied for.
     */
    private int appCount = 0;

    /**
     * The title of the accepted internship placement.
     */
    private String acceptedInternship = null;

    /**
     * Array to store withdrawal requests made by the student.
     */
    private String[] withdrawalRequests = new String[3];

    /**
     * The count of withdrawal requests made by the student.
     */
    private int withdrawalCount = 0;

    /**
     * Constructs a StudentEntity with the specified details.
     * @param id The unique identifier for the student.
     * @param name The name of the student.
     * @param major The major of the student.
     * @param year The year of study of the student.
     * @param email The email address of the student.
     */
    public StudentEntity(String id, String name, String major, int year, String email) {
        super(id, name, email);
        this.major = major;
        this.year = year;
    }

    /**
     * Gets the major of the student.
     * @return The major of the student.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Gets the year of study of the student.
     * @return The year of study of the student.
     */
    public int getYear() {
        return year;
    }

    /**
     * Checks if the student is eligible to apply for more internships.
     * @return true if the student can apply for more internships, false otherwise.
     */
    public boolean canApply() {
        return appCount < 3 && acceptedInternship == null;
    }

    /**
     * Applies for an internship if the student meets the eligibility criteria.
     * @param internship The internship to apply for.
     */
    public void applyInternship(InternshipEntity internship) {
        if (appCount >= 3) {
            System.out.println("You can only apply for 3 internships at once.");
            return;
        }

        if (acceptedInternship != null) {
            System.out.println("You have already accepted an internship placement.");
            return;
        }

        if (year < 3 && !internship.getLevel().equalsIgnoreCase("Basic")) {
            System.out.println("Year 1-2 students can only apply for Basic-level internships.");
            return;
        }

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

    /**
     * Views the internships that the student has applied for, along with their application statuses.
     */
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

    /**
     * Updates the status of a specific internship application.
     * @param internshipTitle The title of the internship.
     * @param status The new status of the application.
     */
    public void updateApplicationStatus(String internshipTitle, String status) {
        for (int i = 0; i < appCount; i++) {
            if (appliedInternships[i].equals(internshipTitle)) {
                applicationStatus[i] = status;
                System.out.println("Application status updated to: " + status);
                return;
            }
        }
    }

    /**
     * Accepts an internship placement if the application status is 'Successful'.
     * @param internshipTitle The title of the accepted internship.
     */
    public void acceptPlacement(String internshipTitle) {
        for (int i = 0; i < appCount; i++) {
            if (appliedInternships[i].equals(internshipTitle) && applicationStatus[i].equals("Successful")) {
                acceptedInternship = internshipTitle;
                System.out.println("Internship placement accepted: " + internshipTitle);
                
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

    /**
     * Requests to withdraw from an internship application or accepted placement.
     * @param internshipTitle The title of the internship to withdraw from.
     */
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

        for (int i = 0; i < withdrawalCount; i++) {
            if (withdrawalRequests[i] != null && withdrawalRequests[i].equals(internshipTitle)) {
                System.out.println("Withdrawal request for '" + internshipTitle + "' is already pending approval.");
                return;
            }
        }

        if (withdrawalCount < 3) {
            withdrawalRequests[withdrawalCount++] = internshipTitle;
            System.out.println("Withdrawal request submitted for: " + internshipTitle);
            System.out.println("Pending approval from Career Center Staff.");
        } else {
            System.out.println("Maximum withdrawal requests reached.");
        }
    }

    /**
     * Gets the list of withdrawal requests made by the student.
     * @return An array of withdrawal request titles.
     */
    public String[] getWithdrawalRequests() {
        return withdrawalRequests;
    }

    /**
     * Gets the count of withdrawal requests made by the student.
     * @return The number of withdrawal requests.
     */
    public int getWithdrawalCount() {
        return withdrawalCount;
    }

    /**
     * Approves a withdrawal request for an internship.
     * @param internshipTitle The title of the internship to withdraw from.
     */
    public void approveWithdrawal(String internshipTitle) {
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

        for (int i = 0; i < withdrawalCount; i++) {
            if (withdrawalRequests[i] != null && withdrawalRequests[i].equals(internshipTitle)) {
                withdrawalRequests[i] = null;
                break;
            }
        }
    }

    /**
     * Releases a slot for an internship, making it available for other applicants.
     * @param internship The internship for which to release the slot.
     */
    public void releaseSlotForInternship(InternshipEntity internship) {
        if (internship != null && internship.getFilledSlots() > 0) {
            internship.releaseSlot();
        }
    }

    /**
     * Gets the titles of internships that the student has applied for.
     * @return An array of applied internship titles.
     */
    public String[] getAppliedInternships() {
        return appliedInternships;
    }

    /**
     * Gets the application statuses of the internships the student has applied for.
     * @return An array of application statuses.
     */
    public String[] getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Gets the count of internships the student has applied for.
     * @return The number of applications.
     */
    public int getAppCount() {
        return appCount;
    }

    /**
     * Gets the title of an application by its index.
     * @param index The index of the application.
     * @return The title of the application, or null if the index is invalid.
     */
    public String getApplicationTitle(int index) {
        if (index >= 0 && index < appCount) {
            return appliedInternships[index];
        }
        return null;
    }

    /**
     * Gets the internship that the student has accepted.
     * @return The title of the accepted internship, or null if none accepted.
     */
    public String getAcceptedInternship() {
        return acceptedInternship;
    }

    /**
     * Displays the student's information, including user details, major, year, and accepted internship.
     */
    public void showInfo() {
        super.showInfo();
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        if (acceptedInternship != null) {
            System.out.println("Accepted Internship: " + acceptedInternship);
        }
    }
}
