# SC2002 Lab SCE4 Group 2 Assignment Internship Placement System

![Internship System](https://user-images.githubusercontent.com/74038190/225813708-98b745f2-7d22-48cf-9150-083f1b00d6c9.gif)

A Java-based console application for managing internship placements at NTU. This system connects students, company representatives, and career center staff in one centralized platform.

## What This System Does

This application streamlines the entire internship placement process:

- **Students** can browse internships, submit applications, and manage their placements
- **Company Representatives** can post internship opportunities and review applications
- **Career Center Staff** oversee the process by approving companies and handling special requests

## Getting Started

### What You'll Need

- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt
- The CSV data files (included in the repository)

### Running the System

1. **Navigate to the project directory:**

   ```cmd
   cd SC2002_Group_Assignment_25S1
   ```

2. **Compile all Java files:**

   ```cmd
   javac InternshipSystem\*.java
   ```

3. **Start the application:**
   ```cmd
   java InternshipSystem.MainBoundary
   ```

That's it! The system will load all data from the CSV files and present you with the main menu.

## How to Use the System

### For Students

**Login Details:**

- Use your student ID from the CSV file (e.g., `U2310001A`)
- Default password: `password`

**What You Can Do:**

1. **View Your Profile** - Check your details and current status
2. **Browse Internships** - See all available opportunities that match your year level and major
3. **Apply for Internships** - Submit up to 3 applications at once
4. **Track Applications** - Monitor the status of your submissions (Pending/Accepted/Rejected)
5. **Accept Placement** - Confirm your internship when offered one
6. **Request Withdrawal** - Ask to withdraw from an accepted internship (requires staff approval)
7. **Change Password** - Update your account password

**Important Notes:**

- Year 1-2 students can only apply for Basic-level internships
- You can have a maximum of 3 active applications
- Once you accept an internship, you can't apply for more positions

### For Company Representatives

**Getting Started:**

- Choose "Register" on your first visit to create an account
- Wait for Career Center Staff to approve your registration
- Then you can log in with your credentials

**What You Can Do:**

1. **View Your Profile** - See your company information
2. **Post Internships** - Create new internship opportunities
3. **Manage Applications** - Review student applications and make decisions
4. **Confirm Placements** - Finalize student placements for your internships
5. **Edit Listings** - Modify your internship postings (title, description, requirements, etc.)
6. **Change Password** - Update your account password

**Creating an Internship:**
When posting a new position, you'll need to specify:

- Title and description
- Level (Basic for junior students, Advanced for senior students)
- Preferred major
- Number of available slots (max 10)
- Opening and closing dates

### For Career Center Staff

**Login Details:**

- Use your staff ID from the CSV file (e.g., `sng001`)
- Default password: `password`

**What You Can Do:**

1. **View Your Profile** - Check your staff details
2. **Approve Company Reps** - Review and approve new company registrations
3. **Approve Internships** - Verify and approve posted internship opportunities
4. **Handle Withdrawals** - Process student withdrawal requests
5. **Generate Reports** - Create filtered reports on internships by:
   - Company name
   - Status (Open/Filled/Cancelled)
   - Level (Basic/Advanced)
   - Preferred major
   - All internships (unfiltered)
6. **Change Password** - Update your account password

## Project Structure

```
SC2002_Group_Assignment_25S1/
│
├── InternshipSystem/           # All Java source files
│   ├── MainBoundary.java       # Main entry point
│   ├── StudentControl.java     # Student functionality
│   ├── CompanyRepControl.java  # Company rep functionality
│   ├── StaffControl.java       # Staff functionality
│   ├── *Entity.java            # Data models
│   ├── *Control.java           # Business logic
│   └── *FilterControl.java     # Report filtering
│
└── sample_*.csv                # Data storage files
    ├── sample_student_list.csv
    ├── sample_staff_list.csv
    ├── sample_company_representative_list.csv
    └── sample_internship_list.csv
```

## Data Files

The system uses CSV files to store all information. These files are automatically loaded when you start the application and saved when you exit.

- **sample_student_list.csv** - Student accounts and details
- **sample_staff_list.csv** - Career center staff accounts
- **sample_company_representative_list.csv** - Company rep accounts and approval status
- **sample_internship_list.csv** - All internship postings and their details

**Important:** Don't delete these files! The system needs them to function. You can edit them manually if needed, but be careful to maintain the CSV format.

## Common Workflows

### For Students Looking for Internships

1. Log in with your student ID
2. Select "View Available Internships" to browse opportunities
3. Note the internship number you're interested in
4. Choose "Apply for Internship" and enter that number
5. Check "View My Applications" to track your application status
6. When accepted, use "Accept Internship Placement" to confirm

### For Companies Posting Positions

1. Register a new account (first time only)
2. Wait for staff approval notification on next login
3. Log in and select "Post New Internship"
4. Fill in all the required details
5. Your posting needs staff approval before students can see it
6. Review applications as they come in
7. Accept suitable candidates and confirm their placements

### For Staff Managing the System

1. Log in with your staff credentials
2. Check for pending company registrations first
3. Review and approve new internship postings
4. Monitor withdrawal requests from students
5. Generate reports as needed for record-keeping

## Tips for Best Experience

- **Always exit properly** (choose Exit from the main menu) so your data saves correctly
- **Check your application count** - students can only have 3 active applications
- **Read internship requirements carefully** - year level and major restrictions apply
- **Staff approval is required** for company registrations and new internship postings
- **Keep your password secure** - you can change it anytime from your user menu

## Troubleshooting

**"Login failed!" message?**

- Double-check your ID (case-sensitive)
- Default password is `password` for all users
- Company reps: Make sure your account is approved by staff

**"No internships available"?**

- Staff may not have approved any yet
- Check if internships match your year level and major requirements
- You might have already accepted an internship

**Application not showing up?**

- Company reps need to check the "View Applications" section
- Students need to check "View My Applications"
- Status updates may take a moment to reflect

**Data not saving?**

- Always use option 4 (Exit) from the main menu
- Don't close the terminal window abruptly
- Check that CSV files aren't opened in other programs

## Development Information

**Course:** SC2002 Object Oriented Design & Programming  
**Academic Year:** 2025 Semester 1  
**Institution:** Nanyang Technological University

This project demonstrates object-oriented programming principles including:

- Inheritance (UserEntity as parent class)
- Polymorphism (Filter control interfaces)
- Encapsulation (Entity classes)
- Control-Boundary-Entity design pattern
- File I/O operations with CSV format

## License

This project is part of an academic assignment. Please refer to the LICENSE file for details.
