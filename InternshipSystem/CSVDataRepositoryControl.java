package InternshipSystem;
import java.io.*;
import java.util.Scanner;

/**
 * Implements the DataRepositoryControl interface for CSV file operations.
 * Provides methods to read, append, and update data in CSV files.
 */
public class CSVDataRepositoryControl implements DataRepositoryControl {

    /**
     * Reads data from a CSV file and returns it as a 2D array.
     * @param filename The name of the CSV file to read from.
     * @param maxRows The maximum number of rows to read.
     * @param maxCols The maximum number of columns to read.
     * @return A 2D array containing the data read from the CSV file.
     */
    @Override
    public String[][] readData(String filename, int maxRows, int maxCols) {
        String[][] data = new String[maxRows][maxCols];
        try {
            Scanner sc = new Scanner(new File(filename));
            int row = 0;
            while (sc.hasNextLine() && row < maxRows) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] fields = line.split(",");
                for (int col = 0; col < fields.length && col < maxCols; col++) {
                    data[row][col] = fields[col].trim();
                }
                row++;
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + filename);
        }
        return data;
    }

    /**
     * Appends a line of data to a CSV file.
     * @param filename The name of the CSV file to append to.
     * @param line The line of data to append.
     */
    @Override
    public void appendData(String filename, String line) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(line + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    /**
     * Updates the company representative data in a CSV file.
     * @param filename The name of the CSV file to update.
     * @param reps The array of company representatives.
     * @param repCount The number of company representatives.
     */
    public void updateCompanyRepData(String filename, CompanyRepEntity[] reps, int repCount) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            fw.write("CompanyRepID,Name,CompanyName,Department,Position,Email,Status\n");
            for (int i = 0; i < repCount; i++) {
                if (reps[i] != null) {
                    String status = reps[i].isApproved() ? "Approved" : "Pending";
                    String line = reps[i].getId() + "," +
                                  reps[i].getName() + "," +
                                  reps[i].getCompanyName() + "," +
                                  reps[i].getDepartment() + "," +
                                  reps[i].getPosition() + "," +
                                  reps[i].getEmail() + "," +
                                  status;
                    fw.write(line + "\n");
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error updating file: " + filename);
        }
    }

    /**
     * Updates the internship data in a CSV file.
     * @param filename The name of the CSV file to update.
     * @param internships The array of internships.
     * @param internshipCount The number of internships.
     */
    public void updateInternshipData(String filename, InternshipEntity[] internships, int internshipCount) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            fw.write("Title,Description,Level,PreferredMajor,OpeningDate,ClosingDate,Status,CompanyName,CompanyRep,Slots,Visible\n");
            for (int i = 0; i < internshipCount; i++) {
                if (internships[i] != null) {
                    String visible = internships[i].getVisibility() ? "true" : "false";
                    String line = internships[i].getTitle() + "," +
                                  internships[i].getDescription() + "," +
                                  internships[i].getLevel() + "," +
                                  internships[i].getPreferredMajor() + "," +
                                  internships[i].getOpeningDate() + "," +
                                  internships[i].getClosingDate() + "," +
                                  internships[i].getStatus() + "," +
                                  internships[i].getCompanyName() + "," +
                                  internships[i].getCompanyRep() + "," +
                                  internships[i].getSlots() + "," +
                                  visible;
                    fw.write(line + "\n");
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error updating file: " + filename);
            e.printStackTrace();
        }
    }
}
