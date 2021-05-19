package com.hibernatetutorial.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernatetutorial.demo.entity.Course;
import com.hibernatetutorial.demo.entity.Instructor;
import com.hibernatetutorial.demo.entity.InstructorDetail;
import com.hibernatetutorial.demo.entity.Review;
import com.hibernatetutorial.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {

		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start the transaction
			session.beginTransaction();
			
			
			// get the student mary from database
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoading student : " + tempStudent);
			System.out.println("\nCourses: " + tempStudent.getCourses());
			
			// create more courses
			Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
			Course tempCourse2 = new Course("Atari 2600 - Game Development");
			
			// add student to course
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			
			// save the courses
			System.out.println("\nSaving the courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("------------       -----------------");
			System.out.println("Done!!");
			System.out.println("------------       -----------------");
			
		}
		finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
