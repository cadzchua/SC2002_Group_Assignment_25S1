package InternshipSystem;

public class StatusFilter implements ReportFilter {
    @Override
    public boolean matches(Internship internship, String filterValue) {
        return internship.getStatus().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "status";
    }
}
