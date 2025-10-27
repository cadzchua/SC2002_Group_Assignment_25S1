package InternshipSystem;

public class AllFilter implements ReportFilter {
    @Override
    public boolean matches(Internship internship, String filterValue) {
        return true;
    }
    
    @Override
    public String getFilterName() {
        return "all";
    }
}
