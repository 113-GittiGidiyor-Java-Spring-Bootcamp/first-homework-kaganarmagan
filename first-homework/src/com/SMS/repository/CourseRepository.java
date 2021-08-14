package com.SMS.repository;

import com.SMS.models.Course;
import com.SMS.models.Instructor;
import com.SMS.models.Student;

import java.util.List;

public interface CourseRepository {
    Instructor findInstructorOfCourse(String prefix);
    List<Student> findStudentsOfCourse(String prefix);
    void deleteCourseFromDatabase(String prefix);
    Course findByPrefix(String prefix);
}
