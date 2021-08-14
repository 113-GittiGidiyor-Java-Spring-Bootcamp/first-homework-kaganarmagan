package com.SMS.controller;

import com.SMS.models.Course;
import com.SMS.models.Instructor;
import com.SMS.models.Student;
import com.SMS.service.CourseService;

import java.util.List;

public class CourseController {

    private CourseService courseService=new CourseService();

    public Course findCourse(int courseId){
        return courseService.findById(courseId);
    }

    public Instructor findInstructor(String prefix){
        return courseService.findInstructorOfCourse(prefix);
    }

    public List<Course> findAllCourses(){
        return courseService.findAll();
    }

    public void saveCourse(Course course){
        courseService.saveToDatabase(course);
    }

    public void deleteCourse(String prefix){
        courseService.deleteCourseFromDatabase(prefix);
    }

    public void deleteCourse(int id){
        courseService.deleteFromDatabase(id);
    }

    public void deleteCourse(Course course){
        courseService.deleteFromDatabase(course);
    }

    public void updateCourse(Course course, int id){
        courseService.updateOnDatabase(course, id);
    }

    public List<Student> findStudentsOfCourse(String prefix){
        return courseService.findStudentsOfCourse(prefix);
    }

}
