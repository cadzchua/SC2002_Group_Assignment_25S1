package InternshipSystem;

public class CSVReaderControl {
    private static DataRepositoryControl repository = new CSVDataRepositoryControl();

    public static String[][] readCSV(String filename, int maxRows, int maxCols) {
        return repository.readData(filename, maxRows, maxCols);
    }

    public static void appendCSV(String filename, String line) {
        repository.appendData(filename, line);
    }

    public static void updateCompanyRepCSV(String filename, CompanyRepEntity[] reps, int repCount) {
        if (repository instanceof CSVDataRepositoryControl) {
            ((CSVDataRepositoryControl) repository).updateCompanyRepData(filename, reps, repCount);
        }
    }

    public static void updateInternshipCSV(String filename, InternshipEntity[] internships, int internshipCount) {
        if (repository instanceof CSVDataRepositoryControl) {
            ((CSVDataRepositoryControl) repository).updateInternshipData(filename, internships, internshipCount);
        }
    }
}
