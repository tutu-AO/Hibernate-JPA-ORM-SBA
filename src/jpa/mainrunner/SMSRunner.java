/*
 * Filename: SMSRunner.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.mainrunner;

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
//import jpa.service.StudentCourseService;
import jpa.service.StudentService;

/**1
 * 
 * @author Harry
 *
 */
public class SMSRunner {

	//instances for objects we will be using 
	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	
	//initializing our instances
	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		SMSRunner sms = new SMSRunner(); // instances of our runner class
		sms.run(); 
	}

	//where program begins
	private void run() {
		
		int userChoice = 1;
		while(userChoice == 1) { // run until user enter any number not equal to 1
			
			userChoice = menu1();
			
			// Login or quit
			switch (userChoice) {
			case 1:
				if (studentLogin()) {
					registerMenu();
				}
				break;
			case 2:
				out.println("Goodbye!");
				break;

			default:
				out.println("Enter 1 or 2. Goodbye!");
				break;
			}
		}
		
	}

	//function prompt user to login or quit and collect user's input
	private int menu1() {
		
		sb.append("\n1. Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}
	
	// function to get user email and password for validation
	private boolean studentLogin() throws NullPointerException {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();
		
		Student students = studentService.getStudentByEmail(email);
		
		if (students != null) {
			currentStudent = students;
		}
		
		try {
			if (currentStudent != null & currentStudent.getStudentPassword().equals(password)) {
				List<Course> courses = studentService.getStudentCourses(email);
				out.println("\nMyClasses:");
				if(courses.size() == 0) {
					out.println("No class(es) registered");
				}else {
					out.printf("%-10s%-30S%-15S\n", "#", "Course", "Instructor");
					for (Course course : courses) {
						out.println(course);
					}
				}
				
				retValue = true;
			}else {
				out.println("User Validation failed. GoodBye!"); // user in our system but entered wrong email or password
			}
		}catch(Exception e) {
			out.println("User is not a student. GoodBye!");  // exception will catch user's not our system
		}
			
		return retValue;	
		
	}
	
	// function to help student select courses for registration
	private void registerMenu() {
		sb.append("\n1. Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getStudentEmail());
			allCourses.removeAll(studentCourses);
			System.out.println("\nAll Courses:");
			out.printf("%-10s%-30S%-15S\n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.GetCourseById(number);
			
			if (newCourse != null) {
				
				studentService.registerStudentToCourse(currentStudent.getStudentEmail(), newCourse.getCId());
				Student temp = studentService.getStudentByEmail(currentStudent.getStudentEmail());
				
				
				StudentService sService = new StudentService();
				List<Course> sCourses = sService.getStudentCourses(temp.getStudentEmail());
				

				out.println("\nMyClasses:");
				out.printf("%-10s%-30S%-15S\n", "#", "Course", "Instructor");
				for (Course course : sCourses) {
					out.println(course);
				}
			}
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
