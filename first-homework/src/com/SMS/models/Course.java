package com.SMS.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String courseName;
    private String prefix;
    private int creditScore;

    @ManyToMany
    private List<Student> students=new ArrayList<>();

    @ManyToOne
    private Instructor instructor;

    public Course(String courseName, String prefix, int creditScore) {
        this.courseName = courseName;
        this.prefix = prefix;
        this.creditScore = creditScore;
    }

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public List<Student> getStudentList() {
        return students;
    }

    public void setStudentList(List<Student> studentList) {
        this.students = studentList;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {//TODO hata olabilir.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return prefix.equals(course.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix);
    }
}
