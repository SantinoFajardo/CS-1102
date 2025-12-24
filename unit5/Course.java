/**
 * Course Class
 * 
 * This class represents a course in the Course Enrollment and Grade Management System.
 * It stores course information including course code, name, and maximum capacity.
 * It also tracks the total number of enrolled students across all course instances.
 */
public class Course {
    // Private instance variables
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment; // Track current enrollment for this course
    
    // Static variable to track total enrolled students across all courses
    private static int totalEnrolledStudents = 0;
    
    /**
     * Constructor to initialize a Course object
     * 
     * @param courseCode The unique code for the course
     * @param courseName The name of the course
     * @param maxCapacity The maximum number of students that can enroll
     */
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }
    
    /**
     * Getter method for course code
     * 
     * @return The course code
     */
    public String getCourseCode() {
        return courseCode;
    }
    
    /**
     * Getter method for course name
     * 
     * @return The course name
     */
    public String getCourseName() {
        return courseName;
    }
    
    /**
     * Getter method for maximum capacity
     * 
     * @return The maximum capacity of the course
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    /**
     * Getter method for current enrollment
     * 
     * @return The current number of enrolled students
     */
    public int getCurrentEnrollment() {
        return currentEnrollment;
    }
    
    /**
     * Method to increment enrollment count for this course
     * Also increments the static total enrolled students counter
     */
    public void incrementEnrollment() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            totalEnrolledStudents++;
        }
    }
    
    /**
     * Method to check if the course has available spots
     * 
     * @return true if there are available spots, false otherwise
     */
    public boolean hasAvailableSpots() {
        return currentEnrollment < maxCapacity;
    }
    
    /**
     * Static method to retrieve the total number of enrolled students
     * across all instances of the Course class
     * 
     * @return The total number of enrolled students
     */
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
    
    /**
     * Override equals method to compare courses by course code
     * 
     * @param obj The object to compare
     * @return true if courses have the same code, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return courseCode != null ? courseCode.equals(course.courseCode) : course.courseCode == null;
    }
    
    /**
     * Override hashCode method for proper HashMap usage
     * 
     * @return The hash code based on course code
     */
    @Override
    public int hashCode() {
        return courseCode != null ? courseCode.hashCode() : 0;
    }
}

