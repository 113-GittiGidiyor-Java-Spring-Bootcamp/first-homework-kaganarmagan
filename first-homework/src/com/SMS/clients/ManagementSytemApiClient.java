package com.SMS.clients;

import com.SMS.controller.CourseController;
import com.SMS.models.*;
import com.SMS.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ManagementSytemApiClient {
    public static void main(String[] args) {
        //saveTestData();

        CourseController controller=new CourseController();

        /*
            Tested saving new course with instructor of its prerequisite course
         */
         //Course chem = new Course("Kimya 2", "CHE102", 3);
         //chem.setInstructor(controller.findInstructor("CHE101"));
         //controller.saveCourse(chem);

        /*
            Tested delete a course with its prefix
         */
        //controller.deleteCourse("MAT101");

        /*
            Tested finding all students who registered for a specific course */
        List<Student> returnedList = controller.findStudentsOfCourse("PHY101");
        for (Student student : returnedList) {
            System.out.println(student.toString());
        }

        System.exit(0);
    }

    private static void saveTestData() {
        Student stu1=new Student("Ali Veli","İstanbul,Kadıköy",LocalDate.of(1998,Month.APRIL,10),"Erkek");
        Student stu2=new Student("Fatma Ayşe","İstanbul,Ataşehir",LocalDate.of(1994,Month.DECEMBER,4),"Kadın");
        Student stu3=new Student("Mustafa Kağan","İstanbul,Beyoğlu",LocalDate.of(1996,Month.AUGUST,13),"Erkek");
        Student stu4=new Student("Ceren Serpil","İstanbul,Beyoğlu",LocalDate.of(1996, Month.JANUARY,1),"Kadın");


        Instructor ins1=new PermanentInstructor("Hoca1","İstanbul,Üsküdar",1234567890L,2826);
        Instructor ins2=new PermanentInstructor("Hoca2","İstanbul,Beyoğlu",2345678901L,2826);
        Instructor ins3=new VisitingResearcher("Hoca3","İstanbul,Beşiktaş",3456789012L,100);

        Course course1=new Course("Matematik 1","MAT101",4);
        Course course2=new Course("Matematik 2","MAT102",4);
        Course course3=new Course("Fizik 1","PHY101",3);
        Course course4=new Course("Kimya 1","CHE101",3);

        course1.setInstructor(ins1);
        course2.setInstructor(ins1);
        course3.setInstructor(ins2);
        course4.setInstructor(ins3);

        course1.getStudentList().add(stu1);
        course1.getStudentList().add(stu3);

        course2.getStudentList().add(stu2);

        course3.getStudentList().add(stu1);
        course3.getStudentList().add(stu3);

        course4.getStudentList().add(stu1);
        course4.getStudentList().add(stu2);

        EntityManager em=EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();

            em.persist(ins1);
            em.persist(ins2);
            em.persist(ins3);

            em.persist(course1);
            em.persist(course2);
            em.persist(course3);
            em.persist(course4);

            em.persist(stu1);
            em.persist(stu2);
            em.persist(stu3);
            em.persist(stu4);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }finally{
            EntityManagerUtils.closeEntityManager(em);
        }

    }
}
