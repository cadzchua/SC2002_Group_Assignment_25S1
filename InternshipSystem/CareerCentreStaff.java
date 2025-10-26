package InternshipSystem;


public class CareerCentreStaff extends User {
    private String department;

    public CareerCentreStaff(String id, String name, String department, String email) {
        super(id, name, email);
        this.department = department;
    }

    public void approveCompanyRep(CompanyRep rep) {
        rep.setApproved(true);
        System.out.println("Company Representative " + rep.getName() + " approved!");
    }

    public void rejectCompanyRep(CompanyRep rep) {
        System.out.println("Company Representative " + rep.getName() + " rejected.");
    }

    public void updateCompanyRepCSV(CompanyRep[] reps, int repCount) {
        CSVReader.updateCompanyRepCSV("sample_company_representative_list.csv", reps, repCount);
    }

    public void updateInternshipCSV(Internship[] internships) {
        int internshipCount = 0;
        for (Internship internship : internships) {
            if (internship != null) {
                internshipCount++;
            }
        }
        CSVReader.updateInternshipCSV("sample_internship_list.csv", internships, internshipCount);
    }

    public void approveInternship(Internship internship) {
        internship.setStatus("Approved");
        System.out.println("Internship \"" + internship.getTitle() + "\" approved!");
    }

    public void rejectInternship(Internship internship) {
        internship.setStatus("Rejected");
        System.out.println("Internship \"" + internship.getTitle() + "\" rejected.");
    }

    public void approveWithdrawal(Student student, String internshipTitle) {
        student.approveWithdrawal(internshipTitle);
        System.out.println("Withdrawal approved for student " + student.getName() + 
                           " from internship: " + internshipTitle);
    }

    public void rejectWithdrawal(Student student, String internshipTitle) {
        System.out.println("Withdrawal rejected for student " + student.getName() + 
                           " from internship: " + internshipTitle);
    }

    public void generateReport(Internship[] internships, String filterType, String filterValue) {
        System.out.println("\n=== Internship Report ===");
        System.out.println("Filter: " + filterType + " = " + filterValue);
        System.out.println("------------------------");
        
        int count = 0;
        for (Internship internship : internships) {
            if (internship == null) continue;
            
            boolean include = false;
            switch (filterType.toLowerCase()) {
                case "status":
                    include = internship.getStatus().equalsIgnoreCase(filterValue);
                    break;
                case "major":
                    include = internship.getPreferredMajor().equalsIgnoreCase(filterValue);
                    break;
                case "level":
                    include = internship.getLevel().equalsIgnoreCase(filterValue);
                    break;
                case "company":
                    include = internship.getCompanyName().equalsIgnoreCase(filterValue);
                    break;
                case "all":
                    include = true;
                    break;
                default:
                    System.out.println("Invalid filter type.");
                    return;
            }
            
            if (include) {
                internship.showInfo();
                System.out.println("Applicants: " + internship.getApplicantCount());
                System.out.println("------------------------");
                count++;
            }
        }
        
        System.out.println("Total internships found: " + count);
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Department: " + department);
    }
}


