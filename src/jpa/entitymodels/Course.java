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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//this is the entity(POJO) that relates to the course table
@Entity
@Table(name="Course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int cId;
	
	@Column(name="name", length=50, nullable=false)
	private String cName;
	
	@Column(name="Instructor", length=50 ,nullable=false)
	private String cInstructorName;
	
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="Student_Course",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_email")
			)
	private List<Student> cStudents;
	
	// default constructor
	public Course() {
		
	}

	// constructor with parameters
	public Course(int cId, String cName, String cInstructorName) {
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	// getters and setters for this class
	public int getCId() {
		return this.cId;
	}

	public void setCId(int cId) {
		this.cId = cId;
	}

	public String getCName() {
		return this.cName;
	}

	public void setCName(String cName) {
		this.cName = cName;
	}

	public String getCInstructorName() {
		return this.cInstructorName;
	}

	public void setCInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}
	
	public List<Student> getStudents() {
		return this.cStudents;
	}

	public void setStudents(List<Student> students) {
		this.cStudents = students;
	}
	
	//use this method to add students taking this course
	public void addStudent(Student theStudent) {
		
		if (cStudents == null) {
			cStudents = new ArrayList<>();
		}
		
		cStudents.add(theStudent);
	}

	//method formats our course data and return as a String
	@Override
	public String toString() {
		
		String res = String.format("%-10s%-30S%-15s", cId, cName, cInstructorName);
		return res;
	}
	
	
}
