package InternshipSystem;


public class CareerCentreStaffEntity extends UserEntity {
    private String department;

    public CareerCentreStaffEntity(String id, String name, String department, String email) {
        super(id, name, email);
        this.department = department;
    }

    public void approveCompanyRep(CompanyRepEntity rep) {
        rep.setApproved(true);
        System.out.println("Company Representative " + rep.getName() + " approved!");
    }

    public void rejectCompanyRep(CompanyRepEntity rep) {
        System.out.println("Company Representative " + rep.getName() + " rejected.");
    }

    public void updateCompanyRepCSV(CompanyRepEntity[] reps, int repCount) {
        CSVReaderControl.updateCompanyRepCSV("sample_company_representative_list.csv", reps, repCount);
    }

    public void updateInternshipCSV(InternshipEntity[] internships) {
        int internshipCount = 0;
        for (InternshipEntity internship : internships) {
            if (internship != null) {
                internshipCount++;
            }
        }
        CSVReaderControl.updateInternshipCSV("sample_internship_list.csv", internships, internshipCount);
    }

    public void approveInternship(InternshipEntity internship) {
        internship.setStatus("Approved");
        System.out.println("Internship \"" + internship.getTitle() + "\" approved!");
    }

    public void rejectInternship(InternshipEntity internship) {
        internship.setStatus("Rejected");
        System.out.println("Internship \"" + internship.getTitle() + "\" rejected.");
    }

    public void approveWithdrawal(StudentEntity student, String internshipTitle) {
        student.approveWithdrawal(internshipTitle);
        System.out.println("Withdrawal approved for student " + student.getName() + 
                           " from internship: " + internshipTitle);
    }

    public void rejectWithdrawal(StudentEntity student, String internshipTitle) {
        System.out.println("Withdrawal rejected for student " + student.getName() + 
                           " from internship: " + internshipTitle);
    }

    public void generateReport(InternshipEntity[] internships, String filterType, String filterValue) {
        ReportFilterControl filter = createFilter(filterType);
        
        if (filter == null) {
            System.out.println("Invalid filter type.");
            return;
        }
        
        System.out.println("\n=== Internship Report ===");
        System.out.println("Filter: " + filter.getFilterName() + " = " + filterValue);
        System.out.println("------------------------");
        
        int count = 0;
        for (InternshipEntity internship : internships) {
            if (internship == null) continue;
            
            if (filter.matches(internship, filterValue)) {
                internship.showInfo();
                System.out.println("Applicants: " + internship.getApplicantCount());
                System.out.println("------------------------");
                count++;
            }
        }
        
        System.out.println("Total internships found: " + count);
    }

    private ReportFilterControl createFilter(String filterType) {
        switch (filterType.toLowerCase()) {
            case "status":
                return new StatusFilterControl();
            case "major":
                return new MajorFilterControl();
            case "level":
                return new LevelFilterControl();
            case "company":
                return new CompanyFilterControl();
            case "all":
                return new AllFilterControl();
            default:
                return null;
        }
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Department: " + department);
    }
}
