package com.example.controller;

import com.example.utils.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS_LIST = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping(path =  "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS_LIST.stream().filter(student -> student.getStudentId().equals(studentId)).findFirst().orElseThrow(()->
                new IllegalStateException("Student id : " + studentId + " does not exists in our logbook.")
        );
    }
}
