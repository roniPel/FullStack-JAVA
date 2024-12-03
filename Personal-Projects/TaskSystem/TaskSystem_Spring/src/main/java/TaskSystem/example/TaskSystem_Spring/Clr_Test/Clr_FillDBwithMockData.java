package TaskSystem.example.TaskSystem_Spring.Clr_Test;

import TaskSystem.example.TaskSystem_Spring.Beans.*;
import TaskSystem.example.TaskSystem_Spring.Repositories.ProjectRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.TaskRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.UsersRepository;
import TaskSystem.example.TaskSystem_Spring.Utils.DateFactory;
import TaskSystem.example.TaskSystem_Spring.Utils.FillDbUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
@RequiredArgsConstructor
public class Clr_FillDBwithMockData implements CommandLineRunner {
    private final TaskRepository taskRepo;
    private final ProjectRepository projectRepo;
    private final UsersRepository usersRepo;
    private Map<String, Object> mockDataMap;
    private final FillDbUtil fillDbUtil;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CLR is running!");
        PrepareSystemData();

        // Data to insert into DB
        int numberOfUsers = (int)mockDataMap.get("numberOfUsers");
        int numberTasksPerUser = (int)mockDataMap.get("numberTasksPerUser");
        int numberProjectsPerUser = (int)mockDataMap.get("numberProjectsPerUser");
        int numTasksPerProject = (int)mockDataMap.get("numTasksPerProject");

        try{
            AddAdminUserCredentials();
            FillInUserTable(numberOfUsers);
            CreateProjectsForUsers(numberProjectsPerUser);
            CreateTasksForUsers(numberTasksPerUser, numTasksPerProject);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void CreateTasksForUsers(int numberTasksPerUser, int numTasksPerProject) {
        int numberOfUsers = (int) usersRepo.count();
        int numberOfProjects = (int) projectRepo.count();

        if(numberOfUsers>0 && numberOfProjects>0) {
            List<UserDetails> users = usersRepo.findAll();
            List<Project> projects = projectRepo.findAll();

            for (UserDetails user: users){
                for (int i = 1; i <= numberTasksPerUser; i++) {
                    int userId = user.getId();
                    String title = "Title"+i;
                    String description = "Description"+i;
                    LocalDate dueDate = DateFactory.getRandomLocalDate();
                    Priority priority = Priority.GetRandomPriority();
                    int projectId = (int)(Math.random()*projects.size())+1;
                    Project project = projects.get(projectId);
                    // Create Task
                    Task task = Task.builder()
                            .id(i)
                            .title(title)
                            .description(description)
                            .due_date(dueDate)
                            .priority(priority)
                            .project(project)
                            .build();
                    taskRepo.save(task);

                    // Todo - Link task to project?
                    //LinkProjectsAndTasks(numTasksPerProject);
                }
            }
        }
    }

    private void CreateProjectsForUsers(int numberProjectsPerUser) {
        int numberOfUsers = (int) usersRepo.count();

        if(numberOfUsers>0) {
            // Get all users
            List<UserDetails> users = usersRepo.findAll();

            for (int i = 0; i < users.size(); i++) {
                for (int j = 1; j <= numberProjectsPerUser; j++) {
                    int userId = users.get(i).getId();
                    String title = "Title" + j + " User" + userId;
                    String details = "Details" + j + " User" + userId;
                    Project project = Project.builder()
                            .id(j)
                            .userId(userId)
                            .title(title)
                            .details(details)
                            .build();
                    projectRepo.save(project);
                }
            }
        }
    }

    private void FillInUserTable(int numberOfUsers) {
        for (int i=1; i<=numberOfUsers; i++) {
            String name = "User"+i;
            String email = "User"+i+"@email.com";
            String password = "PassUser"+i;
            UserDetails user = UserDetails.builder()
                    .id(i)
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
            usersRepo.save(user);
            AddCredentials(name,email,password,UserType.User);
        }
    }

    private void AddAdminUserCredentials() {
        String email = fillDbUtil.getEmailsPassowrdsMap().get("adminEmail");
        String password = fillDbUtil.getEmailsPassowrdsMap().get("adminPassword");
        AddCredentials(email,email,password,UserType.User);
    }

    private void PrepareSystemData() {
        mockDataMap = new HashMap<>();
        mockDataMap.put("numberOfUsers", 10);
        mockDataMap.put("numberTasksPerUser", 5);
        mockDataMap.put("numberProjectsPerUser", 5);
        mockDataMap.put("numTasksPerProject", 2);
    }

    private void AddCredentials(String user, String email, String password, UserType userType) {
        UserDetails userDetails = UserDetails.builder()
                .name(user)
                .email(email)
                .password(password)
                .userType(userType)
                .build();
        usersRepo.save(userDetails);
    }
}
