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

    // GET endpoint 
    @GetMapping("/employees")
    public Employees getEmployees() {
        return employeeManager.getAllEmployees();
    }

    // POST endpoint 
    @PostMapping("/employees")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        Integer id = employeeManager.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        employeeManager.addEmployee(employee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        // Create response map with message
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee added successfully");
        response.put("employee", employee);

        return ResponseEntity.created(location).body(response);
    }
}