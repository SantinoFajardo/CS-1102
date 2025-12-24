import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Administrator Interface
 * 
 * This class provides an interactive command-line interface for administrators
 * to interact with the Course Enrollment and Grade Management System.
 */
public class AdministratorInterface {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method to run the administrator interface
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Course Enrollment and Grade Management System ===");
        System.out.println("Welcome, Administrator!\n");
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    enrollStudent();
                    break;
                case 3:
                    assignGrade();
                    break;
                case 4:
                    calculateOverallGrade();
                    break;
                case 5:
                    displayCourses();
                    break;
                case 6:
                    displayStudents();
                    break;
                case 7:
                    displayEnrollmentStats();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new course");
        System.out.println("2. Enroll a student in a course");
        System.out.println("3. Assign a grade to a student");
        System.out.println("4. Calculate overall course grade for a student");
        System.out.println("5. Display all courses");
        System.out.println("6. Display all students");
        System.out.println("7. Display enrollment statistics");
        System.out.println("8. Exit");
        System.out.println();
    }
    
    /**
     * Handles adding a new course to the system
     */
    private static void addNewCourse() {
        System.out.println("\n--- Add New Course ---");
        
        String courseCode = getStringInput("Enter course code: ");
        String courseName = getStringInput("Enter course name: ");
        int maxCapacity = getIntInput("Enter maximum capacity: ");
        
        if (maxCapacity <= 0) {
            System.out.println("Error: Maximum capacity must be greater than 0.\n");
            return;
        }
        
        Course course = CourseManagement.addCourse(courseCode, courseName, maxCapacity);
        
        if (course != null) {
            System.out.println("Course added successfully!");
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Max Capacity: " + course.getMaxCapacity() + "\n");
        } else {
            System.out.println("Error: Course code already exists. Please use a different code.\n");
        }
    }
    
    /**
     * Handles enrolling a student in a course
     */
    private static void enrollStudent() {
        System.out.println("\n--- Enroll Student in Course ---");
        
        // Get or create student
        Student student = getOrCreateStudent();
        if (student == null) {
            return;
        }
        
        // Display available courses
        ArrayList<Course> courses = CourseManagement.getCourses();
        if (courses.isEmpty()) {
            System.out.println("Error: No courses available. Please add a course first.\n");
            return;
        }
        
        System.out.println("\nAvailable courses:");
        displayCoursesList();
        
        String courseCode = getStringInput("Enter course code to enroll in: ");
        Course course = CourseManagement.findCourseByCode(courseCode);
        
        if (course == null) {
            System.out.println("Error: Course not found.\n");
            return;
        }
        
        boolean success = CourseManagement.enrollStudent(student, course);
        
        if (success) {
            System.out.println("Student enrolled successfully!");
            System.out.println("Student: " + student.getName() + " (ID: " + student.getId() + ")");
            System.out.println("Course: " + course.getCourseName() + " (" + course.getCourseCode() + ")");
            System.out.println("Current Enrollment: " + course.getCurrentEnrollment() + "/" + course.getMaxCapacity() + "\n");
        } else {
            if (!course.hasAvailableSpots()) {
                System.out.println("Error: Course has reached maximum capacity.\n");
            } else if (student.getEnrolledCourses().contains(course)) {
                System.out.println("Error: Student is already enrolled in this course.\n");
            } else {
                System.out.println("Error: Enrollment failed.\n");
            }
        }
    }
    
    /**
     * Handles assigning a grade to a student for a course
     */
    private static void assignGrade() {
        System.out.println("\n--- Assign Grade to Student ---");
        
        // Get student
        String studentId = getStringInput("Enter student ID: ");
        Student student = CourseManagement.findStudentById(students, studentId);
        
        if (student == null) {
            System.out.println("Error: Student not found.\n");
            return;
        }
        
        // Display student's enrolled courses
        ArrayList<Course> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("Error: Student is not enrolled in any courses.\n");
            return;
        }
        
        System.out.println("\nStudent: " + student.getName() + " (ID: " + student.getId() + ")");
        System.out.println("Enrolled courses:");
        for (Course course : enrolledCourses) {
            Double grade = student.getGrade(course);
            String gradeStr = (grade != null) ? String.format("%.2f", grade) : "Not assigned";
            System.out.println("  - " + course.getCourseCode() + ": " + course.getCourseName() + " (Grade: " + gradeStr + ")");
        }
        
        String courseCode = getStringInput("\nEnter course code: ");
        Course course = CourseManagement.findCourseByCode(courseCode);
        
        if (course == null) {
            System.out.println("Error: Course not found.\n");
            return;
        }
        
        if (!student.getEnrolledCourses().contains(course)) {
            System.out.println("Error: Student is not enrolled in this course.\n");
            return;
        }
        
        double grade = getDoubleInput("Enter grade (0.0 - 100.0): ");
        
        if (grade < 0.0 || grade > 100.0) {
            System.out.println("Error: Grade must be between 0.0 and 100.0.\n");
            return;
        }
        
        boolean success = CourseManagement.assignGrade(student, course, grade);
        
        if (success) {
            System.out.println("Grade assigned successfully!");
            System.out.println("Student: " + student.getName());
            System.out.println("Course: " + course.getCourseName());
            System.out.println("Grade: " + String.format("%.2f", grade) + "\n");
        } else {
            System.out.println("Error: Failed to assign grade.\n");
        }
    }
    
    /**
     * Handles calculating overall course grade for a student
     */
    private static void calculateOverallGrade() {
        System.out.println("\n--- Calculate Overall Course Grade ---");
        
        String studentId = getStringInput("Enter student ID: ");
        Student student = CourseManagement.findStudentById(students, studentId);
        
        if (student == null) {
            System.out.println("Error: Student not found.\n");
            return;
        }
        
        double overallGrade = CourseManagement.calculateOverallGrade(student);
        
        if (overallGrade < 0) {
            System.out.println("Error: No grades have been assigned to this student.\n");
        } else {
            System.out.println("\nStudent: " + student.getName() + " (ID: " + student.getId() + ")");
            System.out.println("Overall Course Grade: " + String.format("%.2f", overallGrade));
            
            // Display individual course grades
            System.out.println("\nIndividual Course Grades:");
            HashMap<Course, Double> grades = student.getGrades();
            if (grades.isEmpty()) {
                System.out.println("  No grades assigned.");
            } else {
                for (Course course : grades.keySet()) {
                    Double grade = grades.get(course);
                    if (grade != null) {
                        System.out.println("  - " + course.getCourseCode() + ": " + course.getCourseName() + " - " + String.format("%.2f", grade));
                    }
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Displays all courses in the system
     */
    private static void displayCourses() {
        System.out.println("\n--- All Courses ---");
        displayCoursesList();
    }
    
    /**
     * Helper method to display the list of courses
     */
    private static void displayCoursesList() {
        ArrayList<Course> courses = CourseManagement.getCourses();
        
        if (courses.isEmpty()) {
            System.out.println("No courses available.\n");
        } else {
            for (Course course : courses) {
                System.out.println("Course Code: " + course.getCourseCode());
                System.out.println("Course Name: " + course.getCourseName());
                System.out.println("Enrollment: " + course.getCurrentEnrollment() + "/" + course.getMaxCapacity());
                System.out.println("Available Spots: " + (course.getMaxCapacity() - course.getCurrentEnrollment()));
                System.out.println();
            }
        }
    }
    
    /**
     * Displays all students in the system
     */
    private static void displayStudents() {
        System.out.println("\n--- All Students ---");
        
        if (students.isEmpty()) {
            System.out.println("No students registered.\n");
        } else {
            for (Student student : students) {
                System.out.println("Student ID: " + student.getId());
                System.out.println("Student Name: " + student.getName());
                System.out.println("Enrolled Courses: " + student.getEnrolledCourses().size());
                System.out.println();
            }
        }
    }
    
    /**
     * Displays enrollment statistics
     */
    private static void displayEnrollmentStats() {
        System.out.println("\n--- Enrollment Statistics ---");
        System.out.println("Total Enrolled Students (across all courses): " + Course.getTotalEnrolledStudents());
        System.out.println("Total Courses: " + CourseManagement.getCourses().size());
        System.out.println("Total Registered Students: " + students.size());
        System.out.println();
    }
    
    /**
     * Gets or creates a student based on user input
     * 
     * @return The Student object
     */
    private static Student getOrCreateStudent() {
        String studentId = getStringInput("Enter student ID: ");
        Student student = CourseManagement.findStudentById(students, studentId);
        
        if (student == null) {
            String studentName = getStringInput("Student not found. Enter student name to create new student: ");
            student = new Student(studentName, studentId);
            students.add(student);
            System.out.println("New student created: " + student.getName() + " (ID: " + student.getId() + ")");
        }
        
        return student;
    }
    
    /**
     * Helper method to get string input from user
     * 
     * @param prompt The prompt message
     * @return The string input
     */
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Helper method to get integer input from user with error handling
     * 
     * @param prompt The prompt message
     * @return The integer input
     */
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }
    
    /**
     * Helper method to get double input from user with error handling
     * 
     * @param prompt The prompt message
     * @return The double input
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }
}

