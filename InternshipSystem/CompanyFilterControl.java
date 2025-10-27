package InternshipSystem;

public class CompanyFilterControl implements ReportFilterControl {
    @Override
    public boolean matches(InternshipEntity internship, String filterValue) {
        return internship.getCompanyName().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "company";
    }
}
