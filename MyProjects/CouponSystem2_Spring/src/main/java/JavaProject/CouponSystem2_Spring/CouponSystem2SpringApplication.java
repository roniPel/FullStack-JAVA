package JavaProject.CouponSystem2_Spring;

import JavaProject.CouponSystem2_Spring.Annotations.Programmer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Programmer(author = "Roni Peled", revision = "1.0", connectionType = "Z-Wave")
@EnableScheduling
@SpringBootApplication
public class CouponSystem2SpringApplication {
	public static void main(String[] args) {
		//Todo - Create JavaDocs and go over all warnings - correct documentation
		ApplicationContext ctx = SpringApplication.run(CouponSystem2SpringApplication.class, args);
	}
}