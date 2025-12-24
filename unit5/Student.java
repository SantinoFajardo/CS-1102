import java.util.ArrayList;
import java.util.HashMap;

/**
 * Student Class
 * 
 * This class represents a student in the Course Enrollment and Grade Management System.
 * It stores student information including name, ID, enrolled courses, and grades.
 */
public class Student {
    // Private instance variables
    private String name;
    private String id;
    private ArrayList<Course> enrolledCourses;
    private HashMap<Course, Double> grades; // Maps Course to grade
    
    /**
     * Constructor to initialize a Student object
     * 
     * @param name The name of the student
     * @param id The unique ID of the student
     */
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }
    
    /**
     * Getter method for student name
     * 
     * @return The name of the student
     */
    public String getName() {
        return name;
    }
    
    /**
     * Setter method for student name
     * 
     * @param name The new name of the student
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter method for student ID
     * 
     * @return The ID of the student
     */
    public String getId() {
        return id;
    }
    
    /**
     * Setter method for student ID
     * 
     * @param id The new ID of the student
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Getter method for enrolled courses
     * 
     * @return ArrayList of enrolled courses
     */
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    
    /**
     * Method to enroll a student in a course
     * Adds the course to the student's enrolled courses list
     * 
     * @param course The Course object to enroll in
     */
    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }
    
    /**
     * Method to assign a grade to a student for a specific course
     * Updates the student's grade for that course
     * 
     * @param course The Course object for which the grade is being assigned
     * @param grade The grade to assign (typically 0.0 to 100.0)
     */
    public void assignGrade(Course course, double grade) {
        grades.put(course, grade);
    }
    
    /**
     * Getter method for grades
     * 
     * @return HashMap mapping Course to grade
     */
    public HashMap<Course, Double> getGrades() {
        return grades;
    }
    
    /**
     * Method to get a specific grade for a course
     * 
     * @param course The Course object
     * @return The grade for that course, or null if not assigned
     */
    public Double getGrade(Course course) {
        return grades.get(course);
    }
}
