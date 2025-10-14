package InternshipSystem;


public class CareerCentreStaff extends User {
    private String department;

    public CareerCentreStaff(String id, String name, String department, String email) {
        super(id, name, email);
        this.department = department;
    }

    public void approveCompanyRep(CompanyRep rep) {
        rep.setApproved(true);
        System.out.println("Company Rep " + rep.getName() + " approved!");
        CSVReader.appendCSV("sample_company_representative.csv",
    rep.getId() + "," + rep.getName() + "," + rep.getCompanyName() + "," + rep.getDepartment() + "," + rep.getPosition() + ",true");
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Department: " + department);
    }
}


