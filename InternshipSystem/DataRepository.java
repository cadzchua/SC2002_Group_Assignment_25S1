package InternshipSystem;

public interface DataRepository {
    String[][] readData(String filename, int maxRows, int maxCols);
    void appendData(String filename, String line);
}
