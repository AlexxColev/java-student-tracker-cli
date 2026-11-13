import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository("students.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== STUDENT TRACKER CLI ===");
            System.out.println("1. Add student");
            System.out.println("2. Find student by ID");
            System.out.println("3. Show all students (sorted by name)");
            System.out.println("4. Show top students (sorted by GPA)");
            System.out.println("5. Delete student");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    try {
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Group (e.g. Group-num): ");
                        String group = scanner.nextLine().trim();
                        System.out.print("Enter GPA (0.0 - 10.0): ");
                        double gpa = Double.parseDouble(scanner.nextLine().trim());

                        if (gpa < 0 || gpa > 10) {
                            System.out.println("GPA must be between 0.0 and 10.0.");
                            continue;
                        }

                        boolean added = repo.addStudent(new Student(id, name, group, gpa));
                        System.out.println(added ? "Student added and saved!" : "Error: ID already exists.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid GPA format. Please enter a numeric value.");
                    }
                }
                case "2" -> {
                    System.out.print("Enter ID to search: ");
                    String id = scanner.nextLine().trim();
                    Student found = repo.findById(id);
                    System.out.println(found != null ? found : "Student not found.");
                }
                case "3" -> printList(repo.getAllSortedByName());
                case "4" -> printList(repo.getAllSortedByGpaDesc());
                case "5" -> {
                    System.out.print("Enter ID to delete: ");
                    String id = scanner.nextLine().trim();
                    System.out.println(repo.removeById(id) ? "Deleted." : "Student not found.");
                }
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Unknown command.");
            }
        }
    }

    private static void printList(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            students.forEach(System.out::println);
        }
    }
}