import manager.StudentManager;
import model.Student;
import report.GPAReport;
import report.DepartmentReport;
import thread.AutoSaveTask;
import thread.ReportTask;



public class Main {
    public static void main(String[] args) {

        // loads records from file if it exists
        StudentManager manager = new StudentManager();

        // the start of auto save thread
        AutoSaveTask autoSaveTask = new AutoSaveTask(manager);
        Thread autoSaveThread = new Thread(autoSaveTask); 

       
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();

        
        System.out.println("1- CREATE"); //create new student object
        Student s1 = new Student("451223465", "Lamar Fahad", "IT", 4.5);
        Student s2 = new Student("451273738", "Sara Saqer", "CS", 3.6);

        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(new Student("451283986", "Ghala Omar", "COE", 4.0));

        System.out.println("\n2- READ");
        for (Student s : manager.getAllStudents()) {
            System.out.println(s);
        }

        System.out.println("\n3- UPDATE");
        if (manager.updateStudent("451223465", "Lamar Fahad", "IT", 4.75)) {
            System.out.println("Student 451223465 updated successfully.");
        }

        System.out.println("\n4- READ (Find by ID)");
        Student foundStudent = manager.findStudentById("451223465");
        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        } else {
            System.out.println("Student not found.");
        }

        System.out.println("\n5- DELETE");
        if (manager.removeStudent("451273738")) {
            System.out.println("Student 451273738 removed successfully.");
        }

        System.out.println("\n6- Final list");
        for (Student s : manager.getAllStudents()) {
            System.out.println(s);
        }

        // run reports in thread 
        GPAReport gpaReport = new GPAReport();
        DepartmentReport deptReport = new DepartmentReport();

        Thread gpaThread = new Thread(new ReportTask(manager, gpaReport));
        Thread deptThread = new Thread(new ReportTask(manager, deptReport));

        gpaThread.start();
        deptThread.start();
        
    }
}

