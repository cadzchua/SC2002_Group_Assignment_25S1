package InternshipSystem;

public class AllFilterControl implements ReportFilterControl {
    @Override
    public boolean matches(InternshipEntity internship, String filterValue) {
        return true;
    }
    
    @Override
    public String getFilterName() {
        return "all";
    }
}
