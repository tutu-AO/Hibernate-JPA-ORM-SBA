/**1
 * 
 * @author alex osei
 *
 */
package jpa.dao;

import java.util.List;
import jpa.entitymodels.Course;

//interface that contains method signature for the course service
public interface CourseDAO {
	
	public abstract List<Course> getAllCourses();

}
