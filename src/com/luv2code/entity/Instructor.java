package com.luv2code.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // Here you might be thinking that when I delete the courses, why don't they stay
    // with the instructor as it is a local property in your opinion. In actual, it is not
    //
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.REFRESH})
    private List<Course> courses;

    // cascade - When we perform some action on the target entity, the same action will be applied to the associated entity.
    // set up relationship between instructor and instructor detail
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    // What this does is, it goes to instructorDetail and searches for its primary key and joins it with the column we have mentioned
    // i.e. instructor_detail_id.
    private InstructorDetail instructorDetail; // Associated entity

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // add convenience method for bi-directional relationship

    public void add(Course... tempCourse) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        for (Course course : tempCourse) {
            courses.add(course);
            course.setInstructor(this);
        }


    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
