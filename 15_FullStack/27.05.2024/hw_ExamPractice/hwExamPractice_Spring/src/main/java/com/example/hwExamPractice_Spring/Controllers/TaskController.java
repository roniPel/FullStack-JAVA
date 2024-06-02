package com.example.hwExamPractice_Spring.Controllers;

import com.example.hwExamPractice_Spring.Beans.Task;
import com.example.hwExamPractice_Spring.Exceptions.TaskException;
import com.example.hwExamPractice_Spring.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody Task task) throws TaskException {
        taskService.AddTask(task);
    }

    @GetMapping("/allTasks")
    public List<Task> getAllTasks(){
        return taskService.AllTasks();
    }

    @GetMapping("/completedTasks")
    public List<Task> getCompletedTasks(){
        return taskService.CompletedTasks();
    }

    @GetMapping("/uncompletedTasks")
    public List<Task> getUncompletedTasks(){
        return taskService.UncompletedTasks();
    }

    @PutMapping("/reportCompleted/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reportCompleted(@RequestBody Task task) throws TaskException {
        taskService.ReportCompleted(task);
    }

    @GetMapping("/taskCompletedBefore/{date}")
    public List<Task> taskCompletedBefore(@RequestBody LocalDate date){
        return taskService.TasksCompletedBefore(date);
    }
}
