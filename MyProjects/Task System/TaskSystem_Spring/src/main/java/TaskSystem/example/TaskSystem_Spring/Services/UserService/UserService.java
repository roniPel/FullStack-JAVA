package TaskSystem.example.TaskSystem_Spring.Services.UserService;

import TaskSystem.example.TaskSystem_Spring.Beans.Project;
import TaskSystem.example.TaskSystem_Spring.Beans.Task;

import java.util.List;

public interface UserService {
    // User Actions
    void SetUserId(int userId);
    void ClearUserId();

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
}
