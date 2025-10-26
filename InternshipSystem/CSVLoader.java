package InternshipSystem;

public class CSVLoader {
    private static int studentCount = 0;
    private static int staffCount = 0;

    public static Student[] loadStudents() {
        String[][] data = CSVReader.readCSV("sample_student_list.csv", 100, 10);
        Student[] students = new Student[100];
        studentCount = 0;
        
        for (int i = 0; i < data.length && data[i][0] != null; i++) {
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

    private static int companyRepCount = 0;

    public static CompanyRep[] loadCompanyReps() {
        String[][] data = CSVReader.readCSV("sample_company_representative_list.csv", 50, 10);
        CompanyRep[] reps = new CompanyRep[50];
        companyRepCount = 0;
        
        for (int i = 0; i < data.length && data[i][0] != null; i++) {
            if (i == 0 && data[i][0].equals("CompanyRepID")) {
                continue;
            }
            
            String id = data[i][0];
            String name = data[i][1];
            String companyName = data[i].length > 2 && data[i][2] != null ? data[i][2] : "";
            String department = data[i].length > 3 && data[i][3] != null ? data[i][3] : "";
            String position = data[i].length > 4 && data[i][4] != null ? data[i][4] : "";
            String email = data[i].length > 5 && data[i][5] != null ? data[i][5] : "";
            String status = data[i].length > 6 && data[i][6] != null ? data[i][6] : "Pending";
            
            CompanyRep rep = new CompanyRep(id, name, companyName, department, position, email);
            if (status.equalsIgnoreCase("Approved")) {
                rep.setApproved(true);
            }
            reps[companyRepCount++] = rep;
        }
        
        return reps;
    }

    public static int getCompanyRepCount() {
        return companyRepCount;
    }

    private static int internshipCount = 0;

    public static Internship[] loadInternships() {
        String[][] data = CSVReader.readCSV("sample_internship_list.csv", 100, 15);
        Internship[] internships = new Internship[100];
        internshipCount = 0;
        
        for (int i = 0; i < data.length && data[i][0] != null; i++) {
            if (i == 0 && data[i][0].equals("Title")) {
                continue;
            }
            
            String title = data[i][0];
            String description = data[i].length > 1 && data[i][1] != null ? data[i][1] : "";
            String level = data[i].length > 2 && data[i][2] != null ? data[i][2] : "Basic";
            String preferredMajor = data[i].length > 3 && data[i][3] != null ? data[i][3] : "";
            String openingDate = data[i].length > 4 && data[i][4] != null ? data[i][4] : "";
            String closingDate = data[i].length > 5 && data[i][5] != null ? data[i][5] : "";
            String status = data[i].length > 6 && data[i][6] != null ? data[i][6] : "Pending";
            String companyName = data[i].length > 7 && data[i][7] != null ? data[i][7] : "";
            String companyRep = data[i].length > 8 && data[i][8] != null ? data[i][8] : "";
            int slots = 5;
            if (data[i].length > 9 && data[i][9] != null) {
                try {
                    slots = Integer.parseInt(data[i][9]);
                } catch (NumberFormatException e) {
                    slots = 5;
                }
            }
            boolean visible = false;
            if (data[i].length > 10 && data[i][10] != null) {
                visible = data[i][10].equalsIgnoreCase("true") || data[i][10].equalsIgnoreCase("yes") || data[i][10].equals("1");
            }
            
            boolean hasWarning = false;
            if (!InputValidator.isNotEmpty(title)) {
                System.out.println("WARNING: Skipping internship at line " + (i+1) + " - Empty title");
                continue;
            }
            
            if (!InputValidator.isValidLevel(level)) {
                System.out.println("WARNING: Invalid level '" + level + "' for internship '" + title + "' - Using 'Basic' as default");
                level = "Basic";
                hasWarning = true;
            } else {
                level = InputValidator.normalizeLevel(level);
            }
            
            if (!InputValidator.isValidMajor(preferredMajor)) {
                System.out.println("WARNING: Invalid major '" + preferredMajor + "' for internship '" + title + "'");
                hasWarning = true;
            } else {
                preferredMajor = InputValidator.normalizeMajor(preferredMajor);
            }
            
            if (!InputValidator.isValidDate(openingDate)) {
                System.out.println("WARNING: Invalid opening date '" + openingDate + "' for internship '" + title + "'");
                hasWarning = true;
            }
            
            if (!InputValidator.isValidDate(closingDate)) {
                System.out.println("WARNING: Invalid closing date '" + closingDate + "' for internship '" + title + "'");
                hasWarning = true;
            } else if (InputValidator.isValidDate(openingDate) && 
                       !InputValidator.isClosingAfterOpening(openingDate, closingDate)) {
                System.out.println("WARNING: Closing date is not after opening date for internship '" + title + "'");
                hasWarning = true;
            }
            
            if (!InputValidator.isValidSlots(slots)) {
                System.out.println("WARNING: Invalid slots '" + slots + "' for internship '" + title + "' - Adjusting to valid range");
                slots = Math.max(1, Math.min(10, slots));
                hasWarning = true;
            }
            
            if (hasWarning) {
                System.out.println("  -> Internship '" + title + "' loaded with warnings. Please review the data.");
            }
            
            Internship internship = new Internship(title, description, level, preferredMajor, 
                                                   openingDate, closingDate, status, companyName, 
                                                   companyRep, slots);
            if (visible) {
                internship.setVisible(true);
            }
            internships[internshipCount++] = internship;
        }
        
        return internships;
    }

    public static int getInternshipCount() {
        return internshipCount;
    }
}
