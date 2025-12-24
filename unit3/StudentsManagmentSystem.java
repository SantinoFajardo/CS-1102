package unit3;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentsManagmentSystem {
    /**
     * Name of the student
     */
    String name;
    /**
     * ID of the student
     */
    String ID;
    /**
     * Age of the student
     */
    int age;
    /**
     * Grade of the student
     */
    int grade;

    /**
     * ArrayList to store the students
     */
    private final static ArrayList<StudentsManagmentSystem> students = new ArrayList<>();

    /**
     * Constructor for the StudentsManagmentSystem class
     * @param name The name of the student
     * @param ID The ID of the student
     * @param age The age of the student
     * @param grade The grade of the student
     */
    StudentsManagmentSystem(String name, String ID, int age, int grade) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.grade = grade;
    }

    /**
     * Main method to run the program
     * @param args The arguments passed to the program
     */
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            boolean exitProgram = false;
            scanner = new Scanner(System.in);
            while (!exitProgram) {
                System.out.println("Please choose an option:");
                System.out.println("1. Add Student");
                System.out.println("2. Update Student");
                System.out.println("3. View Student Details");
                System.out.println("4. Exit");
                System.out.println("Enter your choice (1-4): ");
                String userInput = scanner.nextLine();
                handleUserInput(userInput);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (scanner != null){
                scanner.close();
            }
            
        }
    }

    private static void handleUserInput(String userInput) {
        switch (userInput) {
            case "1" -> addStudent();
            case "2" -> updateStudent();
            case "3" -> viewStudentDetails();
            case "4" -> exit();
        }
    }

    private static void exit() {
        System.out.println("Exiting program...");
        System.exit(0);
    }

    /**
     * Adds a student to the system
     * @param name The name of the student
     * @param ID The ID of the student
     * @param age The age of the student
     * @param grade The grade of the student
     */
    private static void addStudent() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
        System.out.println("Enter the name of the student: ");
        String name = scanner.nextLine();
        System.out.println("Enter the ID of the student: ");
        String ID = scanner.nextLine();
        System.out.println("Enter the age of the student: ");
        int age = scanner.nextInt();
        System.out.println("Enter the grade of the student: ");
        int grade = scanner.nextInt();
        students.add(new StudentsManagmentSystem(name, ID, age, grade));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }

    /**
     * Adds a student to the system
     * @param name The name of the student
     * @param ID The ID of the student
     * @param age The age of the student
     * @param grade The grade of the student
     */
    private static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the student to update: ");
        String ID = scanner.nextLine();
        StudentsManagmentSystem foundStudent = null;
        for (StudentsManagmentSystem student : students) {
            if (student.ID.equals(ID)) {
                foundStudent = student;
                break;
            }
        }
        if (foundStudent == null) {
            System.out.println("Student not found");
            return;
        }

        // Name update
        System.out.println("Enter the new name of the student: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            foundStudent.name = name;
            for (StudentsManagmentSystem student : students) {
                if (student.ID.equals(ID)) {
                    student.name = name;
                    break;
                }
            }
        }
        // Age update
        System.out.println("Enter the new age of the student: ");
        String age = scanner.nextLine();
        if (!age.isEmpty()) {
            if (!(Integer.parseInt(age) > 0)) {
                System.out.println("Invalid age, please enter a valid age");
                return;
            }
            foundStudent.age = Integer.parseInt(age);
            for (StudentsManagmentSystem student : students) {
                if (student.ID.equals(ID)) {
                    student.age = Integer.parseInt(age);
                    break;
                }
            }
        }   
        // Grade update
        System.out.println("Enter the new grade of the student: ");
        String grade = scanner.nextLine();
        if (!grade.isEmpty()) {
            if (!(Integer.parseInt(grade) > 0)) {
                System.out.println("Invalid grade, please enter a valid grade");
                return;
            }
            foundStudent.grade = Integer.parseInt(grade);
            for (StudentsManagmentSystem student : students) {
                if (student.ID.equals(ID)) {
                    student.grade = Integer.parseInt(grade);
                    break;
                }
            }
        }
        System.out.println("Student updated successfully");
    }

    /**
     * Views the details of a student
     * @param ID The ID of the student
     */
    private static void viewStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the student to view details: ");
        String ID = scanner.nextLine();
        System.out.println("================================================");
        for (StudentsManagmentSystem student : students) {
            if (student.ID.equals(ID)) {
                System.out.println("Name: " + student.name);
                System.out.println("ID: " + student.ID);
                System.out.println("Age: " + student.age);
                System.out.println("Grade: " + student.grade);
                break;
            }
        }
        System.out.println("================================================");
    }
    
}