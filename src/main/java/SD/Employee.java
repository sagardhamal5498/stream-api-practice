package SD;

import java.time.LocalDate;
import java.util.Date;

public class Employee {


    private int id;
    private String name;
    private int age;
    private String email;
    private String position;
    private double salary;
    private String department;
    private LocalDate dateOfJoining;
    private String phoneNumber;
    private String address;

    // Constructor
    public Employee(int id, String name, int age, String email, String position, double salary, String department, LocalDate dateOfJoining, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method to print the employee details
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}'+"\n";
    }
}
