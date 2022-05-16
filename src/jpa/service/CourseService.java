/**1
 * 
 * @author alex osei
 *
 */
package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;


public class CourseService implements CourseDAO {
	
	//method gets all courses from course table in the shoolmanagementsystem database
	@Override
	public List<Course> getAllCourses(){
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		List<Course> allCourses;

		try {

			//start transaction
			session.beginTransaction();

			//get all students
			allCourses = session.createQuery("FROM Course", Course.class).getResultList();

			//commit transaction
			session.getTransaction().commit();

		} finally{

			session.close();
			factory.close();
		}
		
		return allCourses;
	}

	//added this method(not inherited from the courseDAO interface) to get a course using the provided course id(primary key)
	public Course GetCourseById(int cId) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Course course;
		
		try {
			//start transaction
			session.beginTransaction();
			
			course = session.get(Course.class, cId);
			
			//commit transaction
			session.getTransaction().commit();
			
		}finally{
			session.close();
			factory.close();
		}
		return course;
	}

}
