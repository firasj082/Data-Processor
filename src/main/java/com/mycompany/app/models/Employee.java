package com.mycompany.app.models;
import com.opencsv.bean.CsvBindByName;

public class Employee {

    private static int count = 0;

    @CsvBindByName(column = "ID")
    private final int id;
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "Department")
    private String department;
    @CsvBindByName(column = "Salary")
    private double salary;

    public Employee(){
        this.id = ++count;
    }
    public Employee(String name, String department, double salary) {

        this.id = ++count;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {return this.id;}
    public String getName() {return this.name;}
    public String getDepartment() {return this.department;}
    public double getSalary() {return this.salary;}

    public void setName(String name) {this.name = name;}
    public void setDepartment(String department) {this.department = department;}
    public void setSalary(double salary) {this.salary = salary;}
}
