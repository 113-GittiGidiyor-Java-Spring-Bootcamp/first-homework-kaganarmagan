package com.SMS.service;

import com.SMS.models.Course;
import com.SMS.models.Instructor;
import com.SMS.models.Student;
import com.SMS.repository.CourseRepository;
import com.SMS.repository.CrudRepository;
import com.SMS.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseService implements CrudRepository<Course>, CourseRepository {

    EntityManager em= EntityManagerUtils.getEntityManager("mysqlPU");

    @Override
    public Instructor findInstructorOfCourse(String prefix) {
        return  findByPrefix(prefix).getInstructor();
    }

    @Override
    public List<Student> findStudentsOfCourse(String prefix) {
        return  findByPrefix(prefix).getStudentList();
    }

    @Override
    public Course findByPrefix(String prefix) {
        return em.createQuery("from Course c WHERE c.prefix =:prefix", Course.class).setParameter("prefix", prefix).getSingleResult();
    }

    @Override
    public List<Course> findAll(){
        return em.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    public Course findById(int id) {
        return  em.find(Course.class, id);
    }

    @Override
    public void saveToDatabase(Course object) {
        try{
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteFromDatabase(Course object) {
        try {
            em.getTransaction().begin();

            Course foundCourse = em.createQuery("from Course c WHERE c.prefix =:prefix", Course.class).setParameter("prefix", object.getPrefix()).getSingleResult();
            em.remove(foundCourse);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteFromDatabase(int id) {
        try {
            em.getTransaction().begin();

            Course foundCourse = em.find(Course.class, id);
            em.remove(foundCourse);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteCourseFromDatabase(String prefix) {
        try {
            em.getTransaction().begin();

            Course foundCourse = em.createQuery("from Course c WHERE c.prefix =:prefix", Course.class).setParameter("prefix", prefix).getSingleResult();
            em.remove(foundCourse);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void updateOnDatabase(Course object, int id) {
        try {
            em.getTransaction().begin();

            Course foundCourse = em.find(Course.class, id);
            foundCourse.setCourseName(object.getCourseName());
            foundCourse.setPrefix(object.getPrefix());
            foundCourse.setCreditScore(object.getCreditScore());
            foundCourse.setInstructor(object.getInstructor());
            foundCourse.setStudentList(object.getStudentList());
            em.merge(foundCourse);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }
}
