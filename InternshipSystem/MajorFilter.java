package InternshipSystem;

public class MajorFilter implements ReportFilter {
    @Override
    public boolean matches(Internship internship, String filterValue) {
        return internship.getPreferredMajor().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "major";
    }
}
