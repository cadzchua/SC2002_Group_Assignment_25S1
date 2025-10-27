package InternshipSystem;

public class CSVReader {
    private static DataRepository repository = new CSVDataRepository();

    public static String[][] readCSV(String filename, int maxRows, int maxCols) {
        return repository.readData(filename, maxRows, maxCols);
    }

    public static void appendCSV(String filename, String line) {
        repository.appendData(filename, line);
    }

    public static void updateCompanyRepCSV(String filename, CompanyRep[] reps, int repCount) {
        if (repository instanceof CSVDataRepository) {
            ((CSVDataRepository) repository).updateCompanyRepData(filename, reps, repCount);
        }
    }

    public static void updateInternshipCSV(String filename, Internship[] internships, int internshipCount) {
        if (repository instanceof CSVDataRepository) {
            ((CSVDataRepository) repository).updateInternshipData(filename, internships, internshipCount);
        }
    }
}


