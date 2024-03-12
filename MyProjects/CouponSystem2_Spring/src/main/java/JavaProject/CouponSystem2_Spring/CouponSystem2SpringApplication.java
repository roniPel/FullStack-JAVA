package JavaProject.CouponSystem2_Spring;

import JavaProject.CouponSystem2_Spring.Annotations.Programmer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Programmer(author = "Roni Peled", revision = "1.0", connectionType = "Z-Wave")
@EnableScheduling
@SpringBootApplication
public class CouponSystem2SpringApplication {
	public static void main(String[] args) {
		//Todo - Test All Exceptions/ behavior
		//Todo - switch cron TIME to "00 02 * * * ?" in application.properties
		ApplicationContext ctx = SpringApplication.run(CouponSystem2SpringApplication.class, args);
	}
}