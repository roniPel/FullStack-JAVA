package SpringPractice.HW_Ex1;

import SpringPractice.HW_Ex1.Beans.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HwEx1Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HwEx1Application.class, args);

		Student s1 = ctx.getBean(Student.class);
		Student s2 = ctx.getBean(Student.class);
		Student s3 = ctx.getBean(Student.class);

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
	}

}
