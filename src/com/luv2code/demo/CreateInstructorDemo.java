package com.luv2code.demo;

import com.luv2code.entity.Course;
import com.luv2code.entity.Instructor;
import com.luv2code.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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
            // create the objects
            Instructor tempInstructor =
                    new Instructor("Vyom", "Yadav", "jackhammervyom@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("www.github.com/Vyom-Yadav", "Football");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor

            // Note: This will ALSO save the details of the object associated
            // because of CascadeType.All
            //
            session.save(tempInstructor);

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
