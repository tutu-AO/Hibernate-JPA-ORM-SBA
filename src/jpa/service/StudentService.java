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

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

public class StudentService implements StudentDAO {
	
	//method gets all students from student table in the shoolmanagementsystem database
	@Override
	public List<Student> getAllStudents(){
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		List<Student> allStudents;
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//get all students
			allStudents = session.createQuery("FROM Student", Student.class).getResultList();
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally{
			
			session.close();
			factory.close();
		}
		
		
		return allStudents;
	}
	
	//method gets one student from the student table using their email(primary key)
	@Override
	public Student getStudentByEmail(String sEmail) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(StudentCourses.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		Student student;

		try {

			//start transaction
			session.beginTransaction();
		
			//get student by their email
			student = session.get(Student.class, sEmail);
		
			//commit transaction
			session.getTransaction().commit();
		
		} finally{
			
			session.close();
			factory.close();
		}
		
		return student;
	}
	
	//method checks if student email matches password
	@Override
	public boolean validateStudent(String sEmail, String sPassword) {
		
		Student student = getStudentByEmail(sEmail);
		if(student == null) {
			return false;
		}
		
		boolean validate = false;
		if(student.getStudentPassword().equals(sPassword)) {
			validate = true;
		}
		
		return validate;
	}
	
	//method registers student to a course if they are not already attending the course
	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
	
		Student student = getStudentByEmail(sEmail);
		
		CourseService cService = new CourseService();
		Course course = cService.GetCourseById(cId);
		
		
		boolean flag = false;
		for(Course c : student.getsCourses()) {
			
			if(c.getCId() == cId) {flag = true;}
		}
			
		if(!flag) { 
				
			//make both student and course objects
			Student newStudent = new Student(student.getStudentEmail(), student.getStudentName(), student.getStudentPassword());
			Course newCourse = new Course(course.getCId(), course.getCName(), course.getCInstructorName());
			
			SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Student.class)
					.addAnnotatedClass(Course.class)
					.addAnnotatedClass(StudentCourses.class)
					.buildSessionFactory();

			Session session = factory.getCurrentSession();

			try {

				//start transaction
				session.beginTransaction();
				
				//create the studentCourses object
				StudentCourses temp = new StudentCourses(student.getStudentEmail(), course.getCId());
				
				//save to the student_course table
				session.save(temp);
				
				// add course to the student
				newStudent.addCourse(newCourse);
				
				//commit transaction
				session.getTransaction().commit();
			
			} finally{
				
				session.close();
				factory.close();
			}
				
		}else {
				
			System.out.println("You are already registered for course");
		}
			
		
	}
	
	//method takes student's email as a parameter and find all courses they are attending
	@Override
	public List<Course> getStudentCourses(String sEmail){
		
		Student student = getStudentByEmail(sEmail);
		return student.getsCourses();
	}

}
