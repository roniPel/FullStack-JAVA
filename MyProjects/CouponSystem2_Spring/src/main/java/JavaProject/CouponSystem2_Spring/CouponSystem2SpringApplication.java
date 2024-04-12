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
		//Todo - in 'company' bean - change cascade to CascadeType.REMOVE
		// + delete 'findCustomerIdByCompanyId' in Customer Repo + Change method in Admin service

		//Todo - need to check why company/customer delete/update sometimes fail
		//Todo - Test All Exceptions/ behavior

		//Todo - Instead of LoginManager, create Login Service + Login controller (Ask Zeev for help)

		ApplicationContext ctx = SpringApplication.run(CouponSystem2SpringApplication.class, args);
	}
}