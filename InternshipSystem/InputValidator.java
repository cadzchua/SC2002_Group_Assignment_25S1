package InternshipSystem;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputValidator {
    
    // Valid internship levels
    private static final String[] VALID_LEVELS = {"Basic", "Intermediate", "Advanced"};
    
    // Valid majors (should match student list)
    private static final String[] VALID_MAJORS = {
        "Computer Science",
        "Data Science & AI", 
        "Computer Engineering",
        "Information Engineering & Media",
        "Electrical & Electronic Engineering",
        "Mechanical Engineering",
        "Aerospace Engineering",
        "Business",
        "Economics"
    };
    
    /**
     * Validates if a date string is in YYYY-MM-DD format and is a valid date
     * @param date The date string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return false;
        }
        
        // Check format: YYYY-MM-DD
        Pattern pattern = Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");
        Matcher matcher = pattern.matcher(date);
        
        if (!matcher.matches()) {
            return false;
        }
        
        // Extract year, month, day
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        // Basic validation
        if (year < 2000 || year > 2100) {
            return false;
        }
        
        // Check days in month
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Leap year check
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }
        
        if (day > daysInMonth[month - 1]) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if a year is a leap year
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    /**
     * Validates if closing date is after opening date
     * @param openingDate Opening date in YYYY-MM-DD format
     * @param closingDate Closing date in YYYY-MM-DD format
     * @return true if closing date is after opening date
     */
    public static boolean isClosingAfterOpening(String openingDate, String closingDate) {
        if (!isValidDate(openingDate) || !isValidDate(closingDate)) {
            return false;
        }
        
        // Simple string comparison works for YYYY-MM-DD format
        return closingDate.compareTo(openingDate) > 0;
    }
    
    /**
     * Validates if the internship level is valid
     * @param level The level to validate
     * @return true if valid
     */
    public static boolean isValidLevel(String level) {
        if (level == null || level.trim().isEmpty()) {
            return false;
        }
        
        for (String validLevel : VALID_LEVELS) {
            if (validLevel.equalsIgnoreCase(level.trim())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Normalizes the level string to proper case
     * @param level The level to normalize
     * @return Normalized level string
     */
    public static String normalizeLevel(String level) {
        if (level == null) return "Basic";
        
        for (String validLevel : VALID_LEVELS) {
            if (validLevel.equalsIgnoreCase(level.trim())) {
                return validLevel;
            }
        }
        return "Basic"; // default
    }
    
    /**
     * Validates if the major is valid
     * @param major The major to validate
     * @return true if valid
     */
    public static boolean isValidMajor(String major) {
        if (major == null || major.trim().isEmpty()) {
            return false;
        }
        
        for (String validMajor : VALID_MAJORS) {
            if (validMajor.equalsIgnoreCase(major.trim())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets a list of valid majors for display
     * @return String of valid majors
     */
    public static String getValidMajorsList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < VALID_MAJORS.length; i++) {
            sb.append((i + 1)).append(". ").append(VALID_MAJORS[i]);
            if (i < VALID_MAJORS.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    /**
     * Normalizes the major string to proper case
     * @param major The major to normalize
     * @return Normalized major string
     */
    public static String normalizeMajor(String major) {
        if (major == null) return "";
        
        for (String validMajor : VALID_MAJORS) {
            if (validMajor.equalsIgnoreCase(major.trim())) {
                return validMajor;
            }
        }
        return major.trim();
    }
    
    /**
     * Validates if the number of slots is within acceptable range
     * @param slots Number of slots
     * @return true if valid (1-10)
     */
    public static boolean isValidSlots(int slots) {
        return slots >= 1 && slots <= 10;
    }
    
    /**
     * Validates if a string is not empty
     * @param str String to validate
     * @return true if not empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * Gets an error message for invalid date format
     * @return Error message
     */
    public static String getDateFormatError() {
        return "Invalid date format! Please use YYYY-MM-DD format (e.g., 2025-03-15)";
    }
    
    /**
     * Gets an error message for invalid level
     * @return Error message
     */
    public static String getLevelError() {
        return "Invalid level! Please enter: Basic, Intermediate, or Advanced";
    }
    
    /**
     * Gets an error message for invalid major
     * @return Error message
     */
    public static String getMajorError() {
        return "Invalid major! Please select from the valid majors list.";
    }
}
