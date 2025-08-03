package com.example.studentapp.Util;

import com.example.studentapp.Models.Student;

public class SessionManager {
    private static Student loggedInStudent;

    public static void setLoggedInStudent(Student student) {
        System.out.println("Setting logged in user: " + student.getUsername());
        loggedInStudent = student;
    }

    public static Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public static void clear() {
        loggedInStudent = null;
    }
}
