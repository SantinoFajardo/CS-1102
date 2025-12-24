# Code Explanation: Course Enrollment and Grade Management System

## Overview
This program is a **Course Enrollment and Grade Management System** that allows administrators to manage courses, enroll students, and assign grades. It's a command-line application that uses object-oriented programming principles to organize the code into separate classes, each with a specific responsibility.

---

## System Architecture

The program is divided into four main classes that work together:

### 1. **Student Class** (`Student.java`)
**Purpose:** Represents a single student in the system.

**What it stores:**
- Student's name and ID
- List of courses the student is enrolled in
- Grades for each course (stored as a HashMap where the course is the key and the grade is the value)

**Key methods:**
- `enrollInCourse()` - Adds a course to the student's enrollment list
- `assignGrade()` - Stores a grade for a specific course
- `getGrade()` - Retrieves a student's grade for a particular course

**Concept demonstrated:** This class shows **encapsulation** - it keeps student data private and provides methods to access and modify it safely.

---

### 2. **Course Class** (`Course.java`)
**Purpose:** Represents a single course that can be offered.

**What it stores:**
- Course code (unique identifier like "CS101")
- Course name (like "Introduction to Programming")
- Maximum capacity (how many students can enroll)
- Current enrollment count (how many students are currently enrolled)

**Special feature:**
- Uses a **static variable** `totalEnrolledStudents` to count all enrollments across ALL courses in the system. This demonstrates the difference between instance variables (specific to each course) and static variables (shared across all courses).

**Key methods:**
- `incrementEnrollment()` - Adds one student to the course and updates the total count
- `hasAvailableSpots()` - Checks if there's room for more students

**Concept demonstrated:** This class shows the use of **static variables** and **instance variables**, and how they differ.

---

### 3. **CourseManagement Class** (`CourseManagement.java`)
**Purpose:** Acts as a central manager that coordinates courses, enrollments, and grades.

**What it does:**
- Maintains a list of all courses in the system (using a static ArrayList)
- Handles the business logic for enrolling students
- Manages grade assignments
- Calculates overall grades (averages) for students

**Key methods:**
- `addCourse()` - Creates a new course and adds it to the system (prevents duplicate course codes)
- `enrollStudent()` - Enrolls a student in a course (checks if course is full, if student is already enrolled, etc.)
- `assignGrade()` - Assigns a grade to a student for a specific course
- `calculateOverallGrade()` - Calculates the average of all grades for a student
- `findCourseByCode()` - Searches for a course by its code
- `findStudentById()` - Searches for a student by their ID

**Concept demonstrated:** This class uses **static methods** - methods that can be called without creating an object, making it act like a utility class that manages system-wide operations.

---

### 4. **AdministratorInterface Class** (`AdministratorInterface.java`)
**Purpose:** Provides the user interface - this is what the administrator interacts with.

**What it does:**
- Displays a menu with options (add course, enroll student, assign grade, etc.)
- Gets input from the user through the keyboard
- Calls the appropriate methods from other classes based on user choices
- Displays results and error messages

**Key features:**
- Uses a **while loop** to keep the program running until the user chooses to exit
- Uses a **switch statement** to handle different menu options
- Includes **input validation** - checks that numbers are valid, grades are in the correct range, etc.
- Handles errors gracefully (like when a course doesn't exist or a student isn't found)

**Concept demonstrated:** This class shows **user interaction**, **control flow** (loops and conditionals), and **error handling**.

---

## How the Classes Work Together

1. **AdministratorInterface** is the entry point - it's where the program starts (`main` method).

2. When an administrator wants to add a course:
   - AdministratorInterface asks for course details
   - Calls `CourseManagement.addCourse()`
   - CourseManagement creates a new `Course` object and stores it

3. When enrolling a student:
   - AdministratorInterface gets student information
   - Creates or finds a `Student` object
   - Calls `CourseManagement.enrollStudent()`
   - CourseManagement checks if enrollment is possible, then updates both the `Student` and `Course` objects

4. When assigning a grade:
   - AdministratorInterface gets student ID and course code
   - Calls `CourseManagement.assignGrade()`
   - CourseManagement updates the `Student` object's grade HashMap

5. When calculating overall grade:
   - AdministratorInterface calls `CourseManagement.calculateOverallGrade()`
   - CourseManagement retrieves all grades from the `Student` object and calculates the average

---

## Key Programming Concepts Demonstrated

1. **Object-Oriented Programming (OOP):**
   - Classes and objects
   - Encapsulation (private variables with public methods)
   - Separation of concerns (each class has a specific job)

2. **Static vs Instance:**
   - Static variables and methods belong to the class itself
   - Instance variables and methods belong to individual objects
   - Example: `totalEnrolledStudents` is static (shared), while `currentEnrollment` is instance (specific to each course)

3. **Data Structures:**
   - `ArrayList` - used to store lists of courses and students
   - `HashMap` - used to store grades (maps a course to a grade value)

4. **Input/Output:**
   - `Scanner` class for reading user input
   - `System.out.println()` for displaying information

5. **Error Handling:**
   - Try-catch blocks for invalid number input
   - Validation checks (course capacity, grade ranges, etc.)
   - User-friendly error messages

---

## Program Flow Example

1. Program starts → AdministratorInterface displays menu
2. User chooses "Add a new course" → Enters course code, name, capacity
3. CourseManagement creates Course object → Stores it in the courses list
4. User chooses "Enroll a student" → Enters student ID and course code
5. CourseManagement checks if enrollment is valid → Updates Student and Course objects
6. User chooses "Assign a grade" → Enters student ID, course code, and grade
7. CourseManagement updates the Student's grade HashMap
8. User chooses "Calculate overall grade" → CourseManagement calculates average from all grades
9. Process continues until user chooses "Exit"

---

## Summary

This program demonstrates a well-organized Java application that uses multiple classes to manage a real-world scenario (course enrollment and grading). Each class has a clear responsibility, and they work together to provide a complete system. The code follows good programming practices like encapsulation, input validation, and clear separation of concerns.

