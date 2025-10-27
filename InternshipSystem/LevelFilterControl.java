package InternshipSystem;

public class LevelFilterControl implements ReportFilterControl {
    @Override
    public boolean matches(InternshipEntity internship, String filterValue) {
        return internship.getLevel().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "level";
    }
}
