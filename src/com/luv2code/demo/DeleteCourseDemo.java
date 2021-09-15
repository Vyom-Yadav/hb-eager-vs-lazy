package com.luv2code.demo;

import com.luv2code.entity.Course;
import com.luv2code.entity.Instructor;
import com.luv2code.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {

            session = factory.getCurrentSession();

            //start a transaction
            session.beginTransaction();

            // get a course
            int courseId = 1;
            Course tempCourse = session.get(Course.class, courseId);

            // delete a course
            session.delete(tempCourse);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception a) {
            a.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }


}
