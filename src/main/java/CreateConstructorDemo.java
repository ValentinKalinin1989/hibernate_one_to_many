import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.SessionFactory;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class CreateConstructorDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //use try with resources
        try (factory; Session session = factory.getCurrentSession()) {
            //create the objects
            Instructor tempInstructor =
                    new Instructor("John", "Wick", "boogerman@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://youtube.come", "boxing");
            //associate the object
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            System.out.println("Saving tempInstuctor: " + tempInstructor);
            session.save(tempInstructor);

            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
