package com.example.employeeservice;

import com.example.employeeservice.controller.EmployeeController;
import com.example.employeeservice.manager.EmployeeManager;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Employees;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeManager employeeManager;

    @Test
    public void testGetEmployees() throws Exception {
        // Arrange: Set up mock data
        Employees employees = new Employees();
        employees.setEmployeeList(Arrays.asList(
                new Employee(9, "Akshat", "Doe", "akshat@example.com", "Developer")
        ));
        when(employeeManager.getAllEmployees()).thenReturn(employees);

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Employees[0].id").value("1"))
                .andExpect(jsonPath("$.Employees[0].firstName").value("John"));
    }

    @Test
    public void testAddEmployee() throws Exception {
        // Arrange: Mock the existing employees to determine the next ID
        Employees existingEmployees = new Employees();
        existingEmployees.setEmployeeList(Arrays.asList(
                new Employee(1, "John", "Doe", "john@example.com", "Developer")
        ));
        when(employeeManager.getAllEmployees()).thenReturn(existingEmployees);

        // Act: Perform POST request to add a new employee
        mockMvc.perform(post("/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"email\":\"jane@example.com\",\"title\":\"Manager\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/employees/add/2"))
                .andExpect(jsonPath("$.message").value("Employee added successfully"))
                .andExpect(jsonPath("$.employee.id").value("2"))
                .andExpect(jsonPath("$.employee.firstName").value("Jane"));

        // Assert: Verify that addEmployee was called with the correct employee
        verify(employeeManager).addEmployee(argThat(employee ->
                employee.getId().equals("2") && employee.getFirstName().equals("Jane")
        ));
    }
}