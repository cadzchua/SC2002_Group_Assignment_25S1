package InternshipSystem;

public class Internship {
    String title;
    String description;
    String level;
    String preferredMajor;
    String startDate;
    String endDate;
    String status;
    String companyName;
    String companyRep;
    int slots;

    public Internship(String title, String description, String level, String preferredMajor, String startDate, String endDate,
                      String status, String companyName, String companyRep, int slots) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.preferredMajor = preferredMajor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.companyName = companyName;
        this.companyRep = companyRep;
        this.slots = slots;
    }

    public void showInfo() {
        System.out.println("Title: " + title + ", Level: " + level + ", Major: " + preferredMajor + ", Status: " + status);
        System.out.println("Company: " + companyName + ", Rep: " + companyRep + ", Slots: " + slots);
        System.out.println("Start: " + startDate + ", End: " + endDate);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


