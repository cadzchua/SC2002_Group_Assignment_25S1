package InternshipSystem;

public class CSVLoader {
    private static int studentCount = 0;
    private static int staffCount = 0;

    public static Student[] loadStudents() {
        String[][] data = CSVReader.readCSV("sample_student_list.csv", 100, 10);
        Student[] students = new Student[100];
        studentCount = 0;
        
        for (int i = 0; i < data.length && data[i][0] != null; i++) {
            // Assuming CSV format: id, name, major, year, email
            String id = data[i][0];
            String name = data[i][1];
            String major = data[i].length > 2 && data[i][2] != null ? data[i][2] : "";
            int year = 1;
            if (data[i].length > 3 && data[i][3] != null) {
                try {
                    year = Integer.parseInt(data[i][3]);
                } catch (NumberFormatException e) {
                    year = 1;
                }
            }
            String email = data[i].length > 4 && data[i][4] != null ? data[i][4] : "";
            
            students[studentCount++] = new Student(id, name, major, year, email);
        }
        
        return students;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static CareerCentreStaff[] loadStaff() {
        String[][] data = CSVReader.readCSV("sample_staff_list.csv", 50, 10);
        CareerCentreStaff[] staff = new CareerCentreStaff[50];
        staffCount = 0;
        
        for (int i = 0; i < data.length && data[i][0] != null; i++) {
            // Assuming CSV format: id, name, department, email
            String id = data[i][0];
            String name = data[i][1];
            String department = data[i].length > 2 && data[i][2] != null ? data[i][2] : "";
            String email = data[i].length > 3 && data[i][3] != null ? data[i][3] : "";
            
            staff[staffCount++] = new CareerCentreStaff(id, name, department, email);
        }
        
        return staff;
    }

    public static int getStaffCount() {
        return staffCount;
    }
}
