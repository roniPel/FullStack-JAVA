package com.example.BackEnd_Spring.Controller;

import com.example.BackEnd_Spring.Beans.Person;
import com.example.BackEnd_Spring.Beans.Task;
import com.example.BackEnd_Spring.Exceptions.TaskException;
import com.example.BackEnd_Spring.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/AddTask")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody Task task) throws TaskException {
        taskService.AddTask(task);
    }

    @DeleteMapping("/DeleteTask/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteTask(@PathVariable int id) throws TaskException {
        taskService.DeleteTask(id);
    }

    @GetMapping(value = {"/GetAllTasks"})
    public List<Task> GetAllTasks(){
        return taskService.GetAllTasks();
    }

    @GetMapping(value = "/GetSingleTask/{id}")
    public Task GetSingleTask(@PathVariable int id) throws TaskException {
        return taskService.GetSingleTask(id);
    }
    @PutMapping("/UpdateTask/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateTask(@RequestBody Task task) throws TaskException {
        taskService.UpdateTask(task);
    }

    @PutMapping("/MarkCompleted/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void MarkCompleted(@PathVariable int id) throws TaskException {
        taskService.MarkCompleted(id);
    }
}
