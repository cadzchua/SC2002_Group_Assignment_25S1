package InternshipSystem;
import java.io.*;
import java.util.Scanner;

public class CSVReader {
    public static String[][] readCSV(String filename, int maxRows, int maxCols) {
        String[][] data = new String[maxRows][maxCols];
        try {
            Scanner sc = new Scanner(new File(filename));
            int row = 0;
            while (sc.hasNextLine() && row < maxRows) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue; // skip empty lines
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

    public static void appendCSV(String filename, String line) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(line + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    public static void updateCompanyRepCSV(String filename, CompanyRep[] reps, int repCount) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            // Write header
            fw.write("CompanyRepID,Name,CompanyName,Department,Position,Email,Status\n");
            // Write all company representatives
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

    public static void updateInternshipCSV(String filename, Internship[] internships, int internshipCount) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            // Write header
            fw.write("Title,Description,Level,PreferredMajor,OpeningDate,ClosingDate,Status,CompanyName,CompanyRep,Slots,Visible\n");
            // Write all internships
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


