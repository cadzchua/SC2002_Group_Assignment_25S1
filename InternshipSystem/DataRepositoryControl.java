package InternshipSystem;
/**
 * Interface for data repository operations.
 * Provides methods to read and append data to a file.
 */
public interface DataRepositoryControl {

    /**
     * Reads data from a file and returns it as a 2D array.
     * @param filename The name of the file to read from.
     * @param maxRows The maximum number of rows to read.
     * @param maxCols The maximum number of columns to read.
     * @return A 2D array containing the data read from the file.
     */
    String[][] readData(String filename, int maxRows, int maxCols);

    /**
     * Appends a line of data to a file.
     * @param filename The name of the file to append to.
     * @param line The line of data to append.
     */
    void appendData(String filename, String line);
}
