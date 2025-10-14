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

    public void createInternship(String title, String desc, String level, String major, String startDate, String endDate, int slots) {
        if (internshipCount >= 5) {
            System.out.println("Maximum 5 internships allowed per company rep.");
            return;
        }
        internships[internshipCount++] = new Internship(title, desc, level, major, startDate, endDate, "Pending", companyName, this.name, slots);
        System.out.println("Internship \"" + title + "\" created and pending approval.");
    }

    public void viewInternships() {
        System.out.println("Your Internships:");
        for (int i = 0; i < internshipCount; i++) {
            if (internships[i] != null) {
                internships[i].showInfo();
            }
        }
    }

    public Internship[] getInternships() {
        return internships;
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
        System.out.println("Approved: " + approved);
    }
}
