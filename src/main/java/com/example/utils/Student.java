package com.example.utils;

import lombok.Getter;

@Getter
public class Student {
    private final Integer studentId;
    private final String studentName;
    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }
}
