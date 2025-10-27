package InternshipSystem;

public class LevelFilter implements ReportFilter {
    @Override
    public boolean matches(Internship internship, String filterValue) {
        return internship.getLevel().equalsIgnoreCase(filterValue);
    }
    
    @Override
    public String getFilterName() {
        return "level";
    }
}
