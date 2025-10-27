package InternshipSystem;

public class MajorFilterControl implements ReportFilterControl {
    @Override
    public boolean matches(InternshipEntity internship, String filterValue) {
        return internship.getPreferredMajor().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "major";
    }
}
