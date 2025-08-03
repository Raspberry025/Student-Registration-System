package com.example.studentapp.Models;

public class Student {
    private String username;
    private String name;
    private String email;
    private String program;
    private String semester;

    public Student(String username, String name, String email, String program, String semester) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.program = program;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
}
