package TaskSystem.example.TaskSystem_Spring.Services.AdminService;

import TaskSystem.example.TaskSystem_Spring.Beans.Project;
import TaskSystem.example.TaskSystem_Spring.Beans.Task;

import java.util.List;

public interface AdminService {
    // Task CRUD Actions:
    int AddTask(Task task);
    Task GetOneTask(int taskId);
    boolean UpdateTask(Task task);
    boolean DeleteTask(int taskId);
    List<Task> GetAllUserTasks(int userId);

    // Project CRUD Actions:
    int AddProject(Project project);
    Project GetOneProject(int projectId);
    boolean UpdateProject(Project project);
    boolean DeleteProject(int projectId);
    List<Project> GetAllUserProjects(int userId);

    // Admin Actions:
    List<Task> GetAllTasks();
    List<Project> GetAllProjects();

}
