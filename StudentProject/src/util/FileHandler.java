package util;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "students.txt";

    // Load student records from file
    public List<Student> loadRecords() {
        List<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);

        // If file does not exist → return empty list (NOT null)
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String dept = parts[2];
                    double gpa = Double.parseDouble(parts[3]);

                    students.add(new Student(id, name, dept, gpa));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return students; // NEVER returns null
    }

    // Save student records to file
    public void saveRecords(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                writer.write(s.getId() + "," + s.getName() + "," +
                             s.getDepartment() + "," + s.getGpa());
                writer.newLine();
            }

        } catch (Exception e) {
            // Handle errors during file writing 
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
