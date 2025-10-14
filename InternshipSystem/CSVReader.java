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
}

