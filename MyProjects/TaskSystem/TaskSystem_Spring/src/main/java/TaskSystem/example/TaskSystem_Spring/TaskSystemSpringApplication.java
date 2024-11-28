package TaskSystem.example.TaskSystem_Spring;

import TaskSystem.example.TaskSystem_Spring.Annotations.Programmer;
import TaskSystem.example.TaskSystem_Spring.Beans.UserDetails;
import TaskSystem.example.TaskSystem_Spring.Beans.UserType;
import TaskSystem.example.TaskSystem_Spring.Repositories.ProjectRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.TaskRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@Programmer(author = "Roni Peled", revision = "1.0", connectionType = "Z-Wave")
//@EnableScheduling
public class TaskSystemSpringApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(TaskSystemSpringApplication.class, args);
//		SpringApplication.run(TaskSystemSpringApplication.class, args);

		System.out.println(Test());
	}

	@Bean
	@ConditionalOnProperty(name = "app.runner.enabled", havingValue = "true")
	public CommandLineRunner conditionalRunner() {
		return args -> {
			System.out.println("Conditional CommandLineRunner running!");
		};
	}

	private static UserDetails Test() {
		System.out.println("Running TEST");
		UserDetails user = UserDetails.builder()
				.name("Name1")
				.email("email1@email.com")
				.password("password1")
				.userType(UserType.User)
				.build();
		return user;
	}

}
