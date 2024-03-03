package JavaProject.CouponSystem2_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.SpringSessionContext;

@SpringBootApplication
public class CouponSystem2SpringApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CouponSystem2SpringApplication.class, args);
	}
}