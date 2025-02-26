package com.example.employeeservice;

import com.example.employeeservice.manager.EmployeeManager;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Employees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagerTest {

    private EmployeeManager employeeManager;

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager(); // Fresh instance for each test
    }

    @Test
    public void testAddEmployee() {
        // Arrange
        Employee employee = new Employee(1, "John", "Doe", "john@example.com", "Developer");

        // Act
        employeeManager.addEmployee(employee);

        // Assert
        Employees employees = employeeManager.getAllEmployees();
        assertEquals(1, employees.getEmployeeList().size());
        assertEquals(employee, employees.getEmployeeList().get(0));
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        Employee employee1 = new Employee(4, "John", "Doe", "john@example.com", "Developer");
        Employee employee2 = new Employee(6, "Jane", "Smith", "jane@example.com", "Manager");
        employeeManager.addEmployee(employee1);
        employeeManager.addEmployee(employee2);

        // Act
        Employees employees = employeeManager.getAllEmployees();

        // Assert
        assertEquals(2, employees.getEmployeeList().size());
        assertTrue(employees.getEmployeeList().contains(employee1));
        assertTrue(employees.getEmployeeList().contains(employee2));
    }
}