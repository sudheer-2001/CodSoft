import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<String> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public void registerStudent(String studentID) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentID);
            System.out.println("Registration successful for course: " + courseCode);
        } else {
            System.out.println("Course is full. Cannot register for course: " + courseCode);
        }
    }

    public void removeStudent(String studentID) {
        if (registeredStudents.contains(studentID)) {
            registeredStudents.remove(studentID);
            System.out.println("Student removed from course: " + courseCode);
        } else {
            System.out.println("Student not found in course: " + courseCode);
        }
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> enrolledCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(String courseCode) {
        enrolledCourses.add(courseCode);
        System.out.println("Enrollment successful for course: " + courseCode);
    }

    public void dropCourse(String courseCode) {
        if (enrolledCourses.contains(courseCode)) {
            enrolledCourses.remove(courseCode);
            System.out.println("Course dropped successfully: " + courseCode);
        } else {
            System.out.println("Course not found in your enrolled courses: " + courseCode);
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nEnrolled Courses: " + enrolledCourses;
    }
}

class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourseListing() {
        System.out.println("\nCourse Listing:");
        for (Course course : courses) {
            System.out.println(course + "\n");
        }
    }

    public void displayStudentEnrollment(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                System.out.println("\n" + student + "\n");
                return;
            }
        }
        System.out.println("Student not found with ID: " + studentID);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Student Enrollment");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    displayStudentEnrollment(studentID);
                    break;
                case 5:
                    System.out.println("Exiting the Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void registerForCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        // Check if the student exists
        boolean studentExists = false;
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                studentExists = true;
                break;
            }
        }

        if (!studentExists) {
            System.out.println("Student not found with ID: " + studentID);
            return;
        }

        displayCourseListing();

        System.out.print("Enter Course Code to Register: ");
        String courseCode = scanner.nextLine();

        // Check if the course exists
        Course selectedCourse = null;
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Course not found with Code: " + courseCode);
            return;
        }

        selectedCourse.registerStudent(studentID);
        selectedCourse.getRegisteredStudents().forEach(s -> {
            for (Student student : students) {
                if (student.getStudentID().equals(s)) {
                    student.enrollCourse(courseCode);
                }
            }
        });
    }

    private void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        // Check if the student exists
        boolean studentExists = false;
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                studentExists = true;
                break;
            }
        }

        if (!studentExists) {
            System.out.println("Student not found with ID: " + studentID);
            return;
        }

        // Display enrolled courses for the student
        System.out.println("\nEnrolled Courses for Student " + studentID + ":");
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                student.getEnrolledCourses().forEach(System.out::println);
                break;
            }
        }

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine();

        // Check if the course exists
        Course selectedCourse = null;
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Course not found with Code: " + courseCode);
            return;
        }

        selectedCourse.removeStudent(studentID);
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                student.dropCourse(courseCode);
                break;
            }
        }
    }
}

public class CourseRegistrationApp {
    public static void main(String[] args) {
        // Sample courses
        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Fundamentals of programming", 30, "Mon/Wed 10:00 AM");
        Course c2 = new Course("MAT201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue/Thu 2:00 PM");
        Course c3 = new Course("ENG102", "English Composition", "Academic writing and critical thinking", 20, "Mon/Fri 1:00 PM");

        // Sample students
        Student s1 = new Student("S101", "John Doe");
        Student s2 = new Student("S102", "Jane Smith");

        // Add courses to the course registration system
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();
        registrationSystem.addCourse(c1);
        registrationSystem.addCourse(c2);
        registrationSystem.addCourse(c3);

        // Add students to the course registration system
        registrationSystem.addStudent(s1);
        registrationSystem.addStudent(s2);

        // Run the course registration system
        registrationSystem.run();
    }
}
