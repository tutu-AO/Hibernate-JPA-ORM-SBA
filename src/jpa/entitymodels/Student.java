/**1
 * 
 * @author alex osei
 *
 */
package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//this is the entity(POJO) that relates to the student table
@Entity
@Table(name="Student")
public class Student {
	
	@Id
	@Column(name="email", length=50, nullable=false)
	private String sEmail;
	
	@Column(name="name", length=50, nullable=false)
	private String sName;
	
	@Column(name="password", length=50, nullable=false)
	private String sPass;
	
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="Student_Course",
			joinColumns=@JoinColumn(name="student_email"),
			inverseJoinColumns=@JoinColumn(name="course_id")
			)	
	
	private List<Course> sCourses;
	
	
	// default constructor
	public Student() {
		
	}
	
	// constructor with parameters
	public Student(String sEmail, String sName, String sPass){		
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
	}
	
	// getters and setters for this class
	public String getStudentEmail() {
		return sEmail;
	}
	
	public void setStudentEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	
	public String getStudentName() {
		return sName;
	}
	
	public void setStudentName(String sName) {
		this.sName = sName;
	}
	
	public String getStudentPassword() {
		return this.sPass;
	}
	
	public void setStudentPassword(String sPass) {
		this.sPass = sPass;
	}
	

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}
	
	//use this method to add courses taking by this student
	public void addCourse(Course theCourse) {
		if (this.sCourses == null) {
			this.sCourses = new ArrayList<>();
		}
		
		this.sCourses.add(theCourse);
	}

	//method formats our student data and return as a String
	@Override
	public String toString() {
		String res = String.format("%-10s%-30S%-15s", sEmail, sName, sPass);
		return res;
	}

}
