import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentGradesSystem {
    private List<Student> students;

    public StudentGradesSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, double grade) {
        students.add(new Student(name, grade));
    }

    public double calculateAverageGrade() {
        double total = 0;
        for (Student student : students) {
            total += student.getGrade();
        }
        return students.size() > 0 ? total / students.size() : 0;
    }

    public void sortStudentsByName() {
        Collections.sort(students, Comparator.comparing(Student::getName));
    }

    public void sortStudentsByGrade() {
        Collections.sort(students, Comparator.comparingDouble(Student::getGrade).reversed());
    }

    public Student searchStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public void printAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentGradesSystem system = new StudentGradesSystem();

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Calculate Average Grade");
            System.out.println("3. Sort Students by Name");
            System.out.println("4. Sort Students by Grade");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Print All Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    double grade = -1;
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Enter student grade (numeric): ");
                            grade = scanner.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a numeric value for the grade.");
                            scanner.nextLine(); // Consume invalid input
                        }
                    }
                    system.addStudent(name, grade);
                    break;
                case 2:
                    System.out.println("Average grade: " + system.calculateAverageGrade());
                    break;
                case 3:
                    system.sortStudentsByName();
                    System.out.println("Students sorted by name.");
                    break;
                case 4:
                    system.sortStudentsByGrade();
                    System.out.println("Students sorted by grade.");
                    break;
                case 5:
                    System.out.print("Enter student name to search: ");
                    String searchName = scanner.nextLine();
                    Student student = system.searchStudentByName(searchName);
                    if (student != null) {
                        System.out.println("Found: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    system.printAllStudents();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
