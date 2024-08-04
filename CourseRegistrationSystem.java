import java.util.Scanner;

public class CourseRegistrationSystem {
    private CourseDatabase courseDatabase;
    private StudentDatabase studentDatabase;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courseDatabase = new CourseDatabase();
        studentDatabase = new StudentDatabase();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Register Course");
            System.out.println("4. Drop Course");
            System.out.println("5. List Courses");
            System.out.println("6. List Students");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    registerCourse();
                    break;
                case 4:
                    dropCourse();
                    break;
                case 5:
                    courseDatabase.listCourses();
                    break;
                case 6:
                    studentDatabase.listStudents();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addCourse() {
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Schedule: ");
        String schedule = scanner.nextLine();

        Course course = new Course(courseCode, title, description, capacity, schedule);
        courseDatabase.addCourse(course);
        System.out.println("Course added successfully.");
    }

    private void addStudent() {
        System.out.print("Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentID, name);
        studentDatabase.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void registerCourse() {
        System.out.print("Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        Student student = studentDatabase.findStudentByID(studentID);
        Course course = courseDatabase.findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.registerCourse(course)) {
                System.out.println("Course registered successfully.");
            } else {
                System.out.println("Failed to register course. It may be full or already registered.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    private void dropCourse() {
        System.out.print("Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        Student student = studentDatabase.findStudentByID(studentID);
        Course course = courseDatabase.findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.dropCourse(course)) {
                System.out.println("Course dropped successfully.");
            } else {
                System.out.println("Failed to drop course. It may not be registered.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.run();
    }
}
