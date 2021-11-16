package com.example.controller;

import com.example.utils.Student;
import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS_LIST = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    // hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('PERMISSION') hasAnyAuthority('PERMISSION')
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINTRAINEE')")
    public List<Student> getStudentsList() {
        return STUDENTS_LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("Added student : " + student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteNewStudent(@PathVariable("studentId") int studentId) {
        System.out.println("Deleted student: " + studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.printf("Updated %d id to %s", studentId, student);
    }
}
