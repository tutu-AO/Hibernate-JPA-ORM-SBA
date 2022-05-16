/**1
 * 
 * @author alex osei
 *
 */
package jpa.junit.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jpa.service.StudentService;

public class StudentServiceTest{
	
	private final StudentService stService = new StudentService();
	
	@Test
	void validateStudentInSystemTest() throws Exception {
	
		Assertions.assertEquals(true, stService.validateStudent("hluckham0@google.ru", "X1uZcoIh0dj")); //user is a student
	}
	
	@Test
	void validateStudentNotInSystemTest() throws Exception {
		
		Assertions.assertEquals(false, stService.validateStudent("aose@gusr.cu", "76sTusa"));  //user is not a student
	
	}

}
