package com.luv2code.demo;

import com.luv2code.entity.Course;
import com.luv2code.entity.Instructor;
import com.luv2code.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

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

            // get the instructor from db
            int theId = 1;

            Query<Instructor> query = session
                    .createQuery("SELECT i FROM Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "WHERE i.id=:theInstructorId ",
                            Instructor.class);

            query.setParameter("theInstructorId", theId);

            Instructor tempInstructor = query.getSingleResult();

            System.out.println("luv2code " + tempInstructor);

            // Commit transaction
            session.getTransaction().commit();
            session.close();

            System.out.println(tempInstructor.getCourses());

            System.out.println("Done!");

        } catch (Exception a) {
            a.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }


}
