package TaskSystem.example.TaskSystem_Spring;

import TaskSystem.example.TaskSystem_Spring.Annotations.Programmer;
import TaskSystem.example.TaskSystem_Spring.Beans.UserDetails;
import TaskSystem.example.TaskSystem_Spring.Beans.UserType;
import TaskSystem.example.TaskSystem_Spring.Repositories.ProjectRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.TaskRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Programmer(author = "Roni Peled", revision = "1.0", connectionType = "Z-Wave")
@EnableScheduling
public class TaskSystemSpringApplication {
	@Autowired
	private static TaskRepository taskRepo;
	@Autowired
	private static ProjectRepository projectRepo;
	@Autowired
	private static UsersRepository usersRepo;

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(TaskSystemSpringApplication.class, args);
//		SpringApplication.run(TaskSystemSpringApplication.class, args);

		Test();
	}

	private static void Test() {
		UserDetails user = UserDetails.builder()
				.name("Name1")
				.email("email1@email.com")
				.password("password1")
				.userType(UserType.User)
				.build();
		usersRepo.save(user);
	}

}
