import java.util.ArrayList;
import java.util.HashMap;

/**
 * CourseManagement Class
 * 
 * This class manages courses, student enrollments, and grades in the system.
 * It uses static variables and methods to maintain system-wide information.
 */
public class CourseManagement {
    // Private static variables
    private static ArrayList<Course> courses = new ArrayList<>();
    private static HashMap<Student, HashMap<Course, Double>> overallCourseGrades = new HashMap<>();
    
    /**
     * Static method to add a new course to the system
     * 
     * @param courseCode The unique code for the course
     * @param courseName The name of the course
     * @param maxCapacity The maximum number of students that can enroll
     * @return The created Course object, or null if course code already exists
     */
    public static Course addCourse(String courseCode, String courseName, int maxCapacity) {
        // Check if course code already exists
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return null; // Course code already exists
            }
        }
        
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courses.add(newCourse);
        return newCourse;
    }
    
    /**
     * Static method to enroll a student in a course
     * 
     * @param student The Student object to enroll
     * @param course The Course object to enroll the student in
     * @return true if enrollment was successful, false if course is full or student already enrolled
     */
    public static boolean enrollStudent(Student student, Course course) {
        // Check if course exists
        if (!courses.contains(course)) {
            return false;
        }
        
        // Check if course has available spots
        if (!course.hasAvailableSpots()) {
            return false;
        }
        
        // Check if student is already enrolled
        if (student.getEnrolledCourses().contains(course)) {
            return false;
        }
        
        // Enroll the student
        student.enrollInCourse(course);
        course.incrementEnrollment();
        
        // Initialize grade entry if not exists
        if (!overallCourseGrades.containsKey(student)) {
            overallCourseGrades.put(student, new HashMap<>());
        }
        
        return true;
    }
    
    /**
     * Static method to assign a grade to a student for a course
     * 
     * @param student The Student object
     * @param course The Course object
     * @param grade The grade to assign (typically 0.0 to 100.0)
     * @return true if grade was assigned successfully, false if student is not enrolled
     */
    public static boolean assignGrade(Student student, Course course, double grade) {
        // Check if student is enrolled in the course
        if (!student.getEnrolledCourses().contains(course)) {
            return false;
        }
        
        // Assign the grade
        student.assignGrade(course, grade);
        
        // Update overall course grades
        if (!overallCourseGrades.containsKey(student)) {
            overallCourseGrades.put(student, new HashMap<>());
        }
        overallCourseGrades.get(student).put(course, grade);
        
        return true;
    }
    
    /**
     * Static method to calculate overall course grade for a student
     * Calculates the average of all grades assigned to the student
     * 
     * @param student The Student object
     * @return The overall grade (average), or -1.0 if no grades are assigned
     */
    public static double calculateOverallGrade(Student student) {
        HashMap<Course, Double> studentGrades = student.getGrades();
        
        if (studentGrades.isEmpty()) {
            return -1.0; // No grades assigned
        }
        
        double sum = 0.0;
        int count = 0;
        
        for (Double grade : studentGrades.values()) {
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        
        if (count == 0) {
            return -1.0;
        }
        
        return sum / count;
    }
    
    /**
     * Static method to get the list of all courses
     * 
     * @return ArrayList of all courses
     */
    public static ArrayList<Course> getCourses() {
        return courses;
    }
    
    /**
     * Static method to find a course by course code
     * 
     * @param courseCode The course code to search for
     * @return The Course object if found, null otherwise
     */
    public static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
    
    /**
     * Static method to find a student by ID from a list of students
     * This is a helper method for the administrator interface
     * 
     * @param students The list of students to search
     * @param studentId The student ID to search for
     * @return The Student object if found, null otherwise
     */
    public static Student findStudentById(ArrayList<Student> students, String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}

