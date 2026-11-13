import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StudentRepository {
    private final Map<String, Student> studentMap = new HashMap<>();
    private final Path storageFile;

    public StudentRepository(String fileName) {
        this.storageFile = Paths.get(fileName);
        loadFromFile();
    }

    public boolean addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return false;
        }
        studentMap.put(student.getId(), student);
        saveToFile();
        return true;
    }

    public Student findById(String id) {
        return studentMap.get(id);
    }

    public boolean removeById(String id) {
        if (studentMap.remove(id) != null) {
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Student> getAllSortedByName() {
        List<Student> list = new ArrayList<>(studentMap.values());
        list.sort(Comparator.comparing(Student::getName));
        return list;
    }

    public List<Student> getAllSortedByGpaDesc() {
        List<Student> list = new ArrayList<>(studentMap.values());
        list.sort(Comparator.comparingDouble(Student::getGpa).reversed());
        return list;
    }

    private void saveToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(storageFile)) {
            for (Student s : studentMap.values()) {
                writer.write(s.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        if (!Files.exists(storageFile)) return;
        try (BufferedReader reader = Files.newBufferedReader(storageFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    Student s = Student.fromCsv(line);
                    studentMap.put(s.getId(), s);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}