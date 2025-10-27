package InternshipSystem;

public class CompanyFilter implements ReportFilter {
    @Override
    public boolean matches(Internship internship, String filterValue) {
        return internship.getCompanyName().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "company";
    }
}
