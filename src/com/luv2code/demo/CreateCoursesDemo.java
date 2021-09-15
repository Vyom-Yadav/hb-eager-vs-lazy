package com.luv2code.demo;

import com.luv2code.entity.Course;
import com.luv2code.entity.Instructor;
import com.luv2code.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreateCoursesDemo {

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
            Instructor tempInstructor =
                    session.get(Instructor.class, theId);

            List<Course> a = tempInstructor.getCourses();
            System.out.println(a);

            // create some courses
            Course tempCourse1 = new Course("Java");
            Course tempCourse2 = new Course("Maven");

            // add courses to instructor
//            tempInstructor.add(tempCourse1, tempCourse2);


            // save the courses
//            session.save(tempCourse1);
//            session.save(tempCourse2);
           /*
           persist() method doesn't guarantee that the identifier value
           will be assigned to the persistent state immediately, the
           assignment might happen at flush time. That's why persist()
           method is useful in long-running conversations with an
           extended Session/persistence context.
           save() returns an identifier, and if an INSERT has to be executed
           to get the identifier, this INSERT happens immediately, no matter
           if you are inside or outside a transaction. This is not good in
           a long-running conversation with an extended Session/persistence
           context.

           In our examples, By saving instructor object, we are trying to save
           courses also. To save the course entity, we need instructor identifier(id).
           To get the identifier, we need to save the instructor first then save the course.
           But, we are trying to save both the objects with single save() method,
           which doesn't work in this case as there is no identifier created yet.
           So, we need to use persist() in this case. Hibernate is responsible
           for creating identifier and assigning it to course. :-)
           */

//            session.persist(tempInstructor);


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
