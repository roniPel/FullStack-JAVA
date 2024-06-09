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
		//Todo - Instead of LoginManager, create Login Service + Login controller (JWT, Section 3)
		// link for authentication additions: https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac

		// Todo - Ask Zeev - how to implement 'Local Storage'/ 'Remember Me' option during login (or any other user actions)?
		ApplicationContext ctx = SpringApplication.run(CouponSystem2SpringApplication.class, args);
	}
}