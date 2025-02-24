package com.example.employeeservice.model;

import lombok.Data;

@Data
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String title;

    // Constructors, getters, and setters are automatically handled by Lombok's @Data
}
