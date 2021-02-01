package client;

import com.fasterxml.jackson.annotation.JsonRootName;

//DTO object
@JsonRootName("Employee")
public class Employee {

    private int id;
    private String employeeName;
    private int salary;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
