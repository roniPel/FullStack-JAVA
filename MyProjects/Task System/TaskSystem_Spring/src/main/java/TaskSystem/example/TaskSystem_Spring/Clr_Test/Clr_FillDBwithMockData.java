package TaskSystem.example.TaskSystem_Spring.Clr_Test;

import TaskSystem.example.TaskSystem_Spring.Beans.UserDetails;
import TaskSystem.example.TaskSystem_Spring.Beans.UserType;
import TaskSystem.example.TaskSystem_Spring.Repositories.ProjectRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.TaskRepository;
import TaskSystem.example.TaskSystem_Spring.Repositories.UsersRepository;
import TaskSystem.example.TaskSystem_Spring.Utils.FillDbUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

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
            CreateTasksForUsers(numberTasksPerUser);
            LinkProjectsAndTasks(numTasksPerProject);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void AddAdminUserCredentials() {
        String email = fillDbUtil.getEmailsPassowrdsMap().get("adminEmail");
        String password = fillDbUtil.getEmailsPassowrdsMap().get("adminPassword");
        AddCredentials();
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
