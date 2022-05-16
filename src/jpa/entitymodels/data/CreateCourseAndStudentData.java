/**1
 * 
 * @author alex osei
 *
 */
package jpa.entitymodels.data;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class CreateCourseAndStudentData{
	
	/* run this method once to insert data to both student and course table 
	 * if you don't have data in both tables already 
	 * */
	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course english = new Course(1, "English", "Anderea Scamaden");
			Course mathematics = new Course(2, "Mathematics", "Eustace Niemetz");
			Course anatomy = new Course(3, "Anatomy", "Reynolds Pastor");
			Course orgChemistry = new Course(4, "Organic Chemistry", "Odessa Belcher");
			Course physics = new Course(5, "Physics", "Dani Swallow");
			Course digLogic = new Course(6, "Digital Logic", "Glenden Reilingen");
			Course oop = new Course(7, "Object Oriented Programming", "Giselle Ardy");
			Course ds = new Course(8, "Data Structures", "Carolan Stoller");
			Course politics = new Course(9, "Politics", "Carmita De Maine");
			Course art = new Course(10, "Art", "Kingsly Doxsey");
			
			//saving the courses to the course database(data not in table yet)
			System.out.println("\nSaving to the course table ...");
			session.save(english);
			session.save(mathematics);
			session.save(anatomy);
			session.save(orgChemistry);
			session.save(physics);
			session.save(digLogic);
			session.save(oop);
			session.save(ds);
			session.save(politics);
			session.save(art);
			
			
			//create the students
			Student hazel = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj");
			Student sonnnie = new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU");
			Student quillan = new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd");
			Student clem = new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S");
			Student thornie = new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz");
			Student harcourt = new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs");
			Student holmes = new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ");
			Student alexandra = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j");
			Student laryssa = new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP");
			Student cahra = new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6");
			
			//saving the students to the student database(data not in table yet)
			System.out.println("\nSaving to the student table ...");
			session.save(hazel);
			session.save(sonnnie);
			session.save(quillan);
			session.save(clem);
			session.save(thornie);
			session.save(harcourt);
			session.save(holmes);
			session.save(alexandra);
			session.save(laryssa);
			session.save(cahra);
			
			
			
			//commit transaction to persist data(data in table)
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}finally {
			
			session.close();
			factory.close();
		}
	}
}


