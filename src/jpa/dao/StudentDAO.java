/**1
 * 
 * @author alex osei
 *
 */
package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

// interface that contains method signatures for the student service
public interface StudentDAO {
	
	public abstract List<Student> getAllStudents();
	public abstract Student getStudentByEmail(String sEmail);
	public abstract boolean validateStudent(String sEmail, String sPassword);
	public abstract void registerStudentToCourse(String sEmail, int cId);
	public abstract List<Course> getStudentCourses(String sEmail);
	

}
