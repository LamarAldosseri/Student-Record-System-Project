package model;


public class Student {

    private String name;
    private String id;
    private String department;
    private double gpa;


    public Student(String id, String name, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }

  
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getGpa() {
        return gpa;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 5.0) { 
            this.gpa = gpa;
        } else {
            
            System.err.println("Invalid GPA value.");
        }
    }

 
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Dept: " + department + ", GPA: " + gpa;
    }
}
