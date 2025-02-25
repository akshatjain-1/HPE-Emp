package com.example.employeeservice.controller;

import com.example.employeeservice.manager.EmployeeManager;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

// REST Controller for managing employees
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    // GET endpoint to fetch all employees

    @GetMapping("/employees")
    public Employees getEmployees() {
        return employeeManager.getAllEmployees();
    }

    // POST endpoint to add a new employee
    @PostMapping("/add")
    public ResponseEntity<Object> 
      addEmployee(@RequestBody Employee employee) {
      
        // Generate ID for the new employee
        Integer id = employeeManager.getAllEmployees()
          .getEmployeeList().size() + 1;
        employee.setId(id);

        // Add employee to the list
        employeeManager.addEmployee(employee);

        // Build location URI for the new employee
        URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
