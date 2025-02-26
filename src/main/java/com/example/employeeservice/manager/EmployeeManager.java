package com.example.employeeservice.manager;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Employees;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeManager {

    private static Employees employees = new Employees();

    static {
        
        employees.getEmployeeList()
          .add(new Employee(1, "Prem", "Tiwari", "prem@gmail.com", "jj"));
        employees.getEmployeeList()
          .add(new Employee(2, "Vikash", "Kumar", "vikash@gmail.com","jj"));
        employees.getEmployeeList()
          .add(new Employee(3, "Ritesh", "Ojha", "ritesh@gmail.com","jj"));
    }

    public Employees getAllEmployees() {
        return employees;
    }
    public void addEmployee(Employee employee) {
        employees.getEmployeeList().add(employee);
    }
}
