/**
 * Provides methods to read and update CSV files.
 */
public class CSVReaderControl {
    private static DataRepositoryControl repository = new CSVDataRepositoryControl();

    /**
     * Reads data from a CSV file.
     * @param filename The name of the CSV file to read.
     * @param maxRows The maximum number of rows to read.
     * @param maxCols The maximum number of columns to read.
     * @return A 2D array containing the data read from the CSV file.
     */
    public static String[][] readCSV(String filename, int maxRows, int maxCols) {
        return repository.readData(filename, maxRows, maxCols);
    }

    /**
     * Appends a line of data to a CSV file.
     * @param filename The name of the CSV file to append to.
     * @param line The line of data to append.
     */
    public static void appendCSV(String filename, String line) {
        repository.appendData(filename, line);
    }

    /**
     * Updates the company representative data in a CSV file.
     * @param filename The name of the CSV file to update.
     * @param reps The array of company representatives.
     * @param repCount The number of company representatives.
     */
    public static void updateCompanyRepCSV(String filename, CompanyRepEntity[] reps, int repCount) {
        if (repository instanceof CSVDataRepositoryControl) {
            ((CSVDataRepositoryControl) repository).updateCompanyRepData(filename, reps, repCount);
        }
    }

    /**
     * Updates the internship data in a CSV file.
     * @param filename The name of the CSV file to update.
     * @param internships The array of internships.
     * @param internshipCount The number of internships.
     */
    public static void updateInternshipCSV(String filename, InternshipEntity[] internships, int internshipCount) {
        if (repository instanceof CSVDataRepositoryControl) {
            ((CSVDataRepositoryControl) repository).updateInternshipData(filename, internships, internshipCount);
        }
    }
}
