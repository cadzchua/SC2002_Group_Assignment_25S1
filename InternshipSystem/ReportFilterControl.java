package InternshipSystem;

public interface ReportFilterControl {
    boolean matches(InternshipEntity internship, String filterValue);
    String getFilterName();
}
