import java.util.Objects;

public class Student {
    private final String id;
    private String name;
    private String group;
    private double gpa;

    public Student(String id, String name, String group, double gpa) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGroup() { return group; }
    public double getGpa() { return gpa; }

    public void setGpa(double gpa) { this.gpa = gpa; }

    public String toCsv() {
        return id + "," + name + "," + group + "," + gpa;
    }

    public static Student fromCsv(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %-6s | Name: %-18s | Group: %-10s | GPA: %.2f", id, name, group, gpa);
    }
}