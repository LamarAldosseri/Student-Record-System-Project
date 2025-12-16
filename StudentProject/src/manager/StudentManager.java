package manager;

import model.Student;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

// Manages the collection of Student objects and handles the operations
// It uses FileHandler for the data
public class StudentManager {
    private List<Student> students;
    private FileHandler fileHandler;

    // Constructor to initializes the file handler and existing records
    public StudentManager() {
        this.fileHandler = new FileHandler();
        // Load records from the file
        this.students = fileHandler.loadRecords();
    }


    
     //Adds a new student if the ID does not already exist
    //return true if added and false if ID already exists
    public boolean addStudent(Student student) {
   // Check for duplicate ID
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false; // If Student with this ID already exists
            }
        }
        
        students.add(student);
        fileHandler.saveRecords(students); // The change to the file
        return true;
    }

   
     //Finds a student by their ID
     //return The Student object if found otherwise null
    public Student findStudentById(String id) {
  
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s; 
            }
        }
        return null; // Student not found
    }

    
      //Returns a copy of the list of all students
      //return A new ArrayList containing all student records
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students);
    }


    
     // Updates the details (name, department, GPA) of an existing student
     //return true if updated and false if student not found
    public boolean updateStudent(String id, String name, String department, double gpa) {
        Student student = findStudentById(id);
        
       
        if (student != null) { // Check if the student exists
            student.setName(name);
            student.setDepartment(department);
            student.setGpa(gpa);
            fileHandler.saveRecords(students); 
            return true;
        }
        return false;
    }

   //Removes a student record by ID
     //Return true if removed and false if student not found
    public boolean removeStudent(String id) {
       
        Student studentToRemove = null;
        for (Student s : students) {
            if (s.getId().equals(id)) {
                studentToRemove = s;
                break;
            }
        }
        
        if (studentToRemove != null) {
            students.remove(studentToRemove); // Remove from the list
            fileHandler.saveRecords(students); 
            return true;
        }
        return false;
    }
    public synchronized void saveAll() {
    fileHandler.saveRecords(students);
    System.out.println("Auto-save done.");
}


    public List<Student> getStudentsByDepartment(String department) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (s.getDepartment().equalsIgnoreCase(department)) {
            result.add(s);
        }
    }
    return result;
}


} 

