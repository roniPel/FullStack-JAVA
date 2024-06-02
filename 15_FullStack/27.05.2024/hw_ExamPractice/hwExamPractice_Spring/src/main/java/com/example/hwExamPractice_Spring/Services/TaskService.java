package com.example.hwExamPractice_Spring.Services;

import com.example.hwExamPractice_Spring.Beans.Task;
import com.example.hwExamPractice_Spring.Exceptions.Errors;
import com.example.hwExamPractice_Spring.Exceptions.TaskException;
import com.example.hwExamPractice_Spring.Repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepo;

    public boolean AddTask(Task task) throws TaskException {
        if(taskRepo.existsById(task.getId())){
            throw new TaskException(Errors.ID_ALREADY_EXISTS);
        }
        taskRepo.saveAndFlush(task);
        return true;
    }

    public boolean ReportCompleted(Task task) throws TaskException {
        if(!taskRepo.existsById(task.getId())){
            throw new TaskException(Errors.ID_NOT_FOUND);
        }
        task.setDateCompleted(LocalDate.now());
        task.setCompleted(true);
        taskRepo.saveAndFlush(task);
        return true;
    }

    public List<Task> AllTasks(){
        return taskRepo.findAll();
    }
    public List<Task> CompletedTasks(){
        return taskRepo.findByIsCompletedTrue();
    }

    public List<Task> UncompletedTasks(){
        return taskRepo.findByIsCompletedFalse();
    }

    public List<Task> TasksCompletedBefore(LocalDate date){
        return taskRepo.findByDateCompletedBefore(date);
    }
}
