package InternshipSystem;

public class StatusFilterControl implements ReportFilterControl {
    @Override
    public boolean matches(InternshipEntity internship, String filterValue) {
        return internship.getStatus().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "status";
    }
}
