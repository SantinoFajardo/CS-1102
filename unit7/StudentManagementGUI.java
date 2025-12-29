import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * StudentManagementGUI Class
 * 
 * This class provides a comprehensive GUI application for managing students,
 * course enrollments, and grades. It uses Java Swing framework to create
 * an intuitive and user-friendly interface.
 * 
 * The GUI includes:
 * - Student Management: Add, update, and view students
 * - Course Enrollment: Enroll students in courses
 * - Grade Management: Assign grades to students
 * 
 * All operations update the interface dynamically and include proper error handling.
 */
public class StudentManagementGUI extends JFrame {
    // Data storage
    private ArrayList<Student> students;
    
    // GUI Components - Menu Bar
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu studentMenu;
    private JMenu courseMenu;
    private JMenu gradeMenu;
    private JMenu helpMenu;
    
    // GUI Components - Main Interface
    private JTabbedPane tabbedPane;
    
    // Student Management Tab Components
    private JPanel studentManagementPanel;
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JTextField studentNameField;
    private JTextField studentIdField;
    private JButton addStudentButton;
    private JButton updateStudentButton;
    private JButton viewStudentsButton;
    private JButton clearFormButton;
    
    // Course Enrollment Tab Components
    private JPanel courseEnrollmentPanel;
    private JComboBox<Course> courseComboBox;
    private JList<Student> eligibleStudentsList;
    private DefaultListModel<Student> eligibleStudentsModel;
    private JButton enrollButton;
    private JLabel courseInfoLabel;
    private JTextArea enrollmentStatusArea;
    
    // Grade Management Tab Components
    private JPanel gradeManagementPanel;
    private JComboBox<Student> studentComboBox;
    private JList<Course> enrolledCoursesList;
    private DefaultListModel<Course> enrolledCoursesModel;
    private JTextField gradeField;
    private JButton assignGradeButton;
    private JTextArea gradeStatusArea;
    private JTable gradesTable;
    private DefaultTableModel gradesTableModel;
    
    /**
     * Constructor to initialize the GUI application
     */
    public StudentManagementGUI() {
        students = new ArrayList<>();
        initializeGUI();
        setupMenuBar();
        setupStudentManagementTab();
        setupCourseEnrollmentTab();
        setupGradeManagementTab();
        addSampleData(); // Add some sample data for demonstration
    }
    
    /**
     * Initialize the main GUI window
     */
    private void initializeGUI() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel if system L&F fails
        }
        
        tabbedPane = new JTabbedPane();
        add(tabbedPane);
    }
    
    /**
     * Setup the menu bar with all menu items
     */
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        
        // File Menu
        fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        
        // Student Menu
        studentMenu = new JMenu("Student");
        JMenuItem addStudentItem = new JMenuItem("Add Student");
        addStudentItem.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
            showAddStudentDialog();
        });
        JMenuItem updateStudentItem = new JMenuItem("Update Student");
        updateStudentItem.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
            showUpdateStudentDialog();
        });
        JMenuItem viewStudentsItem = new JMenuItem("View All Students");
        viewStudentsItem.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
            viewStudentsButton.doClick();
        });
        studentMenu.add(addStudentItem);
        studentMenu.add(updateStudentItem);
        studentMenu.add(viewStudentsItem);
        
        // Course Menu
        courseMenu = new JMenu("Course");
        JMenuItem enrollStudentItem = new JMenuItem("Enroll Student");
        enrollStudentItem.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        courseMenu.add(enrollStudentItem);
        
        // Grade Menu
        gradeMenu = new JMenu("Grade");
        JMenuItem assignGradeItem = new JMenuItem("Assign Grade");
        assignGradeItem.addActionListener(e -> tabbedPane.setSelectedIndex(2));
        gradeMenu.add(assignGradeItem);
        
        // Help Menu
        helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(gradeMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    /**
     * Setup the Student Management tab
     */
    private void setupStudentManagementTab() {
        studentManagementPanel = new JPanel(new BorderLayout(10, 10));
        studentManagementPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Student Name Field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        studentNameField = new JTextField(20);
        formPanel.add(studentNameField, gbc);
        
        // Student ID Field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        studentIdField = new JTextField(20);
        formPanel.add(studentIdField, gbc);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> handleAddStudent());
        updateStudentButton = new JButton("Update Student");
        updateStudentButton.addActionListener(e -> handleUpdateStudent());
        viewStudentsButton = new JButton("View All Students");
        viewStudentsButton.addActionListener(e -> handleViewStudents());
        clearFormButton = new JButton("Clear Form");
        clearFormButton.addActionListener(e -> clearStudentForm());
        
        buttonPanel.add(addStudentButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(viewStudentsButton);
        buttonPanel.add(clearFormButton);
        
        // Table Panel
        String[] columnNames = {"Student ID", "Student Name", "Enrolled Courses", "Average Grade"};
        studentTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        studentTable = new JTable(studentTableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String studentId = (String) studentTableModel.getValueAt(selectedRow, 0);
                    Student student = CourseManagement.findStudentById(students, studentId);
                    if (student != null) {
                        studentNameField.setText(student.getName());
                        studentIdField.setText(student.getId());
                    }
                }
            }
        });
        
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 300));
        
        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        
        studentManagementPanel.add(topPanel, BorderLayout.NORTH);
        studentManagementPanel.add(tableScrollPane, BorderLayout.CENTER);
        
        tabbedPane.addTab("Student Management", studentManagementPanel);
    }
    
    /**
     * Setup the Course Enrollment tab
     */
    private void setupCourseEnrollmentTab() {
        courseEnrollmentPanel = new JPanel(new BorderLayout(10, 10));
        courseEnrollmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top Panel - Course Selection
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        topPanel.add(new JLabel("Select Course:"), gbc);
        gbc.gridx = 1;
        courseComboBox = new JComboBox<>();
        courseComboBox.addActionListener(e -> updateEligibleStudentsList());
        topPanel.add(courseComboBox, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        courseInfoLabel = new JLabel("No course selected");
        topPanel.add(courseInfoLabel, gbc);
        
        // Middle Panel - Eligible Students List
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(BorderFactory.createTitledBorder("Eligible Students (Not Enrolled)"));
        eligibleStudentsModel = new DefaultListModel<>();
        eligibleStudentsList = new JList<>(eligibleStudentsModel);
        eligibleStudentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane studentsScrollPane = new JScrollPane(eligibleStudentsList);
        studentsScrollPane.setPreferredSize(new Dimension(0, 200));
        middlePanel.add(studentsScrollPane, BorderLayout.CENTER);
        
        // Bottom Panel - Enroll Button and Status
        JPanel bottomPanel = new JPanel(new BorderLayout());
        enrollButton = new JButton("Enroll Selected Student");
        enrollButton.addActionListener(e -> handleEnrollStudent());
        bottomPanel.add(enrollButton, BorderLayout.NORTH);
        
        enrollmentStatusArea = new JTextArea(5, 30);
        enrollmentStatusArea.setEditable(false);
        enrollmentStatusArea.setBorder(BorderFactory.createTitledBorder("Enrollment Status"));
        JScrollPane statusScrollPane = new JScrollPane(enrollmentStatusArea);
        bottomPanel.add(statusScrollPane, BorderLayout.CENTER);
        
        // Layout
        courseEnrollmentPanel.add(topPanel, BorderLayout.NORTH);
        courseEnrollmentPanel.add(middlePanel, BorderLayout.CENTER);
        courseEnrollmentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        tabbedPane.addTab("Course Enrollment", courseEnrollmentPanel);
    }
    
    /**
     * Setup the Grade Management tab
     */
    private void setupGradeManagementTab() {
        gradeManagementPanel = new JPanel(new BorderLayout(10, 10));
        gradeManagementPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left Panel - Student and Course Selection
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(300, 0));
        
        // Student Selection
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBorder(BorderFactory.createTitledBorder("Select Student"));
        studentComboBox = new JComboBox<>();
        studentComboBox.addActionListener(e -> updateEnrolledCoursesList());
        studentPanel.add(studentComboBox, BorderLayout.CENTER);
        
        // Enrolled Courses List
        JPanel coursesPanel = new JPanel(new BorderLayout());
        coursesPanel.setBorder(BorderFactory.createTitledBorder("Enrolled Courses"));
        enrolledCoursesModel = new DefaultListModel<>();
        enrolledCoursesList = new JList<>(enrolledCoursesModel);
        enrolledCoursesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane coursesScrollPane = new JScrollPane(enrolledCoursesList);
        coursesScrollPane.setPreferredSize(new Dimension(0, 150));
        coursesPanel.add(coursesScrollPane, BorderLayout.CENTER);
        
        // Grade Input
        JPanel gradeInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        gradeInputPanel.add(new JLabel("Grade (0-100):"), gbc);
        gbc.gridx = 1;
        gradeField = new JTextField(10);
        gradeInputPanel.add(gradeField, gbc);
        
        assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(e -> handleAssignGrade());
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gradeInputPanel.add(assignGradeButton, gbc);
        
        leftPanel.add(studentPanel, BorderLayout.NORTH);
        leftPanel.add(coursesPanel, BorderLayout.CENTER);
        leftPanel.add(gradeInputPanel, BorderLayout.SOUTH);
        
        // Right Panel - Grades Table and Status
        JPanel rightPanel = new JPanel(new BorderLayout());
        
        // Grades Table
        String[] columnNames = {"Course Code", "Course Name", "Grade"};
        gradesTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        gradesTable = new JTable(gradesTableModel);
        JScrollPane gradesScrollPane = new JScrollPane(gradesTable);
        gradesScrollPane.setBorder(BorderFactory.createTitledBorder("Student Grades"));
        rightPanel.add(gradesScrollPane, BorderLayout.CENTER);
        
        // Status Area
        gradeStatusArea = new JTextArea(3, 30);
        gradeStatusArea.setEditable(false);
        gradeStatusArea.setBorder(BorderFactory.createTitledBorder("Status"));
        rightPanel.add(gradeStatusArea, BorderLayout.SOUTH);
        
        // Layout
        gradeManagementPanel.add(leftPanel, BorderLayout.WEST);
        gradeManagementPanel.add(rightPanel, BorderLayout.CENTER);
        
        tabbedPane.addTab("Grade Management", gradeManagementPanel);
    }
    
    /**
     * Handle Add Student button click
     */
    private void handleAddStudent() {
        String name = studentNameField.getText().trim();
        String id = studentIdField.getText().trim();
        
        // Validation
        if (name.isEmpty() || id.isEmpty()) {
            showErrorDialog("Please fill in all fields.");
            return;
        }
        
        // Check if student ID already exists
        if (CourseManagement.findStudentById(students, id) != null) {
            showErrorDialog("Student ID already exists. Please use a different ID.");
            return;
        }
        
        // Create and add student
        Student student = new Student(name, id);
        students.add(student);
        
        // Update UI
        updateStudentTable();
        updateStudentComboBox();
        clearStudentForm();
        
        showSuccessDialog("Student added successfully!");
    }
    
    /**
     * Handle Update Student button click
     */
    private void handleUpdateStudent() {
        String name = studentNameField.getText().trim();
        String id = studentIdField.getText().trim();
        
        // Validation
        if (name.isEmpty() || id.isEmpty()) {
            showErrorDialog("Please fill in all fields.");
            return;
        }
        
        // Find student
        Student student = CourseManagement.findStudentById(students, id);
        if (student == null) {
            showErrorDialog("Student not found. Please check the Student ID.");
            return;
        }
        
        // Update student
        student.setName(name);
        
        // Update UI
        updateStudentTable();
        updateStudentComboBox();
        updateEnrolledCoursesList();
        updateGradesTable();
        clearStudentForm();
        
        showSuccessDialog("Student information updated successfully!");
    }
    
    /**
     * Handle View Students button click
     */
    private void handleViewStudents() {
        updateStudentTable();
    }
    
    /**
     * Handle Enroll Student button click
     */
    private void handleEnrollStudent() {
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        Student selectedStudent = eligibleStudentsList.getSelectedValue();
        
        // Validation
        if (selectedCourse == null) {
            showErrorDialog("Please select a course.");
            return;
        }
        
        if (selectedStudent == null) {
            showErrorDialog("Please select a student to enroll.");
            return;
        }
        
        // Enroll student
        boolean success = CourseManagement.enrollStudent(selectedStudent, selectedCourse);
        
        if (success) {
            enrollmentStatusArea.append("✓ " + selectedStudent.getName() + " enrolled in " + 
                                      selectedCourse.getCourseCode() + "\n");
            updateEligibleStudentsList();
            updateCourseInfo();
            updateStudentTable();
            updateEnrolledCoursesList();
            showSuccessDialog("Student enrolled successfully!");
        } else {
            if (selectedStudent.getEnrolledCourses().contains(selectedCourse)) {
                showErrorDialog("Student is already enrolled in this course.");
            } else if (!selectedCourse.hasAvailableSpots()) {
                showErrorDialog("Course is full. No available spots.");
            } else {
                showErrorDialog("Enrollment failed. Please try again.");
            }
        }
    }
    
    /**
     * Handle Assign Grade button click
     */
    private void handleAssignGrade() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        Course selectedCourse = enrolledCoursesList.getSelectedValue();
        String gradeText = gradeField.getText().trim();
        
        // Validation
        if (selectedStudent == null) {
            showErrorDialog("Please select a student.");
            return;
        }
        
        if (selectedCourse == null) {
            showErrorDialog("Please select a course.");
            return;
        }
        
        if (gradeText.isEmpty()) {
            showErrorDialog("Please enter a grade.");
            return;
        }
        
        // Parse grade
        double grade;
        try {
            grade = Double.parseDouble(gradeText);
            if (grade < 0 || grade > 100) {
                showErrorDialog("Grade must be between 0 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid grade format. Please enter a number.");
            return;
        }
        
        // Assign grade
        boolean success = CourseManagement.assignGrade(selectedStudent, selectedCourse, grade);
        
        if (success) {
            gradeStatusArea.setText("✓ Grade " + grade + " assigned to " + selectedStudent.getName() + 
                                   " for " + selectedCourse.getCourseCode() + "\n");
            updateGradesTable();
            updateStudentTable();
            gradeField.setText("");
            showSuccessDialog("Grade assigned successfully!");
        } else {
            showErrorDialog("Failed to assign grade. Student may not be enrolled in this course.");
        }
    }
    
    /**
     * Update the student table with current data
     */
    private void updateStudentTable() {
        studentTableModel.setRowCount(0);
        for (Student student : students) {
            int courseCount = student.getEnrolledCourses().size();
            double avgGrade = CourseManagement.calculateOverallGrade(student);
            String avgGradeStr = avgGrade >= 0 ? String.format("%.2f", avgGrade) : "N/A";
            studentTableModel.addRow(new Object[]{
                student.getId(),
                student.getName(),
                courseCount,
                avgGradeStr
            });
        }
    }
    
    /**
     * Update the student combo box
     */
    private void updateStudentComboBox() {
        Student selected = (Student) studentComboBox.getSelectedItem();
        studentComboBox.removeAllItems();
        for (Student student : students) {
            studentComboBox.addItem(student);
        }
        if (selected != null && students.contains(selected)) {
            studentComboBox.setSelectedItem(selected);
        }
    }
    
    /**
     * Update the eligible students list based on selected course
     */
    private void updateEligibleStudentsList() {
        eligibleStudentsModel.clear();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        
        if (selectedCourse != null) {
            updateCourseInfo();
            for (Student student : students) {
                if (!student.getEnrolledCourses().contains(selectedCourse) && 
                    selectedCourse.hasAvailableSpots()) {
                    eligibleStudentsModel.addElement(student);
                }
            }
        }
    }
    
    /**
     * Update course information label
     */
    private void updateCourseInfo() {
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        if (selectedCourse != null) {
            courseInfoLabel.setText(String.format(
                "Course: %s - %s | Capacity: %d/%d | Available: %d",
                selectedCourse.getCourseCode(),
                selectedCourse.getCourseName(),
                selectedCourse.getCurrentEnrollment(),
                selectedCourse.getMaxCapacity(),
                selectedCourse.getMaxCapacity() - selectedCourse.getCurrentEnrollment()
            ));
        } else {
            courseInfoLabel.setText("No course selected");
        }
    }
    
    /**
     * Update the enrolled courses list based on selected student
     */
    private void updateEnrolledCoursesList() {
        enrolledCoursesModel.clear();
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        
        if (selectedStudent != null) {
            for (Course course : selectedStudent.getEnrolledCourses()) {
                enrolledCoursesModel.addElement(course);
            }
            updateGradesTable();
        }
    }
    
    /**
     * Update the grades table based on selected student
     */
    private void updateGradesTable() {
        gradesTableModel.setRowCount(0);
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        
        if (selectedStudent != null) {
            for (Course course : selectedStudent.getEnrolledCourses()) {
                Double grade = selectedStudent.getGrade(course);
                String gradeStr = grade != null ? String.format("%.2f", grade) : "Not assigned";
                gradesTableModel.addRow(new Object[]{
                    course.getCourseCode(),
                    course.getCourseName(),
                    gradeStr
                });
            }
        }
    }
    
    /**
     * Update course combo box
     */
    private void updateCourseComboBox() {
        Course selected = (Course) courseComboBox.getSelectedItem();
        courseComboBox.removeAllItems();
        for (Course course : CourseManagement.getCourses()) {
            courseComboBox.addItem(course);
        }
        if (selected != null && CourseManagement.getCourses().contains(selected)) {
            courseComboBox.setSelectedItem(selected);
        }
    }
    
    /**
     * Clear the student form
     */
    private void clearStudentForm() {
        studentNameField.setText("");
        studentIdField.setText("");
        studentTable.clearSelection();
    }
    
    /**
     * Show add student dialog (alternative method)
     */
    private void showAddStudentDialog() {
        clearStudentForm();
        studentNameField.requestFocus();
    }
    
    /**
     * Show update student dialog (alternative method)
     */
    private void showUpdateStudentDialog() {
        if (studentTable.getSelectedRow() < 0) {
            showErrorDialog("Please select a student from the table to update.");
        }
    }
    
    /**
     * Show error dialog
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show success dialog
     */
    private void showSuccessDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Show about dialog
     */
    private void showAboutDialog() {
        String message = "Student Management System\n\n" +
                        "Version 1.0\n\n" +
                        "A comprehensive GUI application for managing students,\n" +
                        "course enrollments, and grades.\n\n" +
                        "Developed using Java Swing framework.";
        JOptionPane.showMessageDialog(this, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Add sample data for demonstration
     */
    private void addSampleData() {
        // Add sample courses
        CourseManagement.addCourse("CS1101", "Programming Fundamentals", 30);
        CourseManagement.addCourse("CS1102", "Programming 1", 25);
        CourseManagement.addCourse("CS1103", "Programming 2", 25);
        CourseManagement.addCourse("MATH1201", "College Algebra", 40);
        CourseManagement.addCourse("ENGL1101", "English Composition", 35);
        
        // Add sample students
        Student student1 = new Student("John Doe", "S001");
        Student student2 = new Student("Jane Smith", "S002");
        Student student3 = new Student("Bob Johnson", "S003");
        
        students.add(student1);
        students.add(student2);
        students.add(student3);
        
        // Update UI
        updateStudentTable();
        updateStudentComboBox();
        updateCourseComboBox();
    }
    
    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        // Run on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                StudentManagementGUI gui = new StudentManagementGUI();
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error starting application: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

