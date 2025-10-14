package InternshipSystem;


public class Student extends User {
    private String major;
    private int year;
    private String[] appliedInternships = new String[3];
    private int appCount = 0;

    public Student(String id, String name, String major, int year, String email) {
        super(id, name, email);
        this.major = major;
        this.year = year;
    }

    public void applyInternship(String title, String level) {
        if (appCount >= 3) {
            System.out.println("You can only apply for 3 internships.");
            return;
        }

        if (year < 3 && !level.equalsIgnoreCase("Basic")) {
            System.out.println("Year 1-2 students can only apply for Basic internships.");
            return;
        }

        appliedInternships[appCount++] = title;
        System.out.println("Applied to: " + title);
    }

    public void viewApplications() {
        System.out.println("Your internship applications:");
        for (int i = 0; i < appCount; i++) {
            System.out.println((i+1) + ". " + appliedInternships[i]);
        }
        if (appCount == 0) System.out.println("No applications yet.");
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
    }
}
