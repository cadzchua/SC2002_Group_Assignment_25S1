package InternshipSystem;

public interface DataRepositoryControl {
    String[][] readData(String filename, int maxRows, int maxCols);
    void appendData(String filename, String line);
}
