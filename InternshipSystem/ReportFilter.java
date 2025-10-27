package InternshipSystem;

public interface ReportFilter {
    boolean matches(Internship internship, String filterValue);
    String getFilterName();
}
