package com.example.controller;

import com.example.utils.Student;
import lombok.Getter;
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

    @GetMapping("/")
    public List<Student> getStudentsList(){
        return STUDENTS_LIST;
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("Added student : "+student);
    }
    @DeleteMapping(path="{studentId}")
    public void deleteNewStudent(@PathVariable("studentId") int studentId) {
        System.out.println("Deleted student: "+ studentId);
    }
    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.printf("Updated %d id to %s",studentId, student);
    }
}
