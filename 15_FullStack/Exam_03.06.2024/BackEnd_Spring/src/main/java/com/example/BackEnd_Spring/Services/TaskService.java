package com.example.BackEnd_Spring.Services;

import com.example.BackEnd_Spring.Beans.Person;
import com.example.BackEnd_Spring.Beans.Task;
import com.example.BackEnd_Spring.Exceptions.Errors;
import com.example.BackEnd_Spring.Exceptions.TaskException;
import com.example.BackEnd_Spring.Repositories.PersonRepository;
import com.example.BackEnd_Spring.Repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepo;
    private final PersonRepository personRepo;
    public boolean AddTask(Task task) throws TaskException {
        if(taskRepo.existsById(task.getId())){
            throw new TaskException(Errors.TASK_ALREADY_EXISTS);
        }
        if(personRepo.existsById(task.getResponsible().getId())){
           throw new TaskException(Errors.PERSON_ALREADY_EXISTS);
        }
        personRepo.saveAndFlush(task.getResponsible());
        taskRepo.saveAndFlush(task);
        return true;
    }

    public boolean DeleteTask(int id) throws TaskException {
        if(!taskRepo.existsById(id)){
            throw new TaskException(Errors.TASK_NOT_FOUND);
        }
        taskRepo.deleteById(id);
        return true;
    }

    public List<Task> GetAllTasks(){
        return taskRepo.findAll();
    }

    public boolean UpdateTask(Task task) throws TaskException {
        int id = task.getId();
        // Verify task exists in DB
        Task currentTask = taskRepo.findById(id).orElseThrow(
                () -> new TaskException(Errors.TASK_NOT_FOUND)
        );
        Person updatedResponsible = task.getResponsible();
        Person currentResponsible = currentTask.getResponsible();
        // Verify the same responsible person is linked to the new task
        if(!(currentResponsible.getId() == updatedResponsible.getId())){
            throw new TaskException(Errors.INCORRECT_PERSON_IN_TASK);
        }
        //Save updated person
        personRepo.saveAndFlush(task.getResponsible());
        //Save updated task
        taskRepo.saveAndFlush(task);
        return true;
    }
    public boolean MarkCompleted(int id) throws TaskException {
        if(!taskRepo.existsById(id)){
            throw new TaskException(Errors.TASK_NOT_FOUND);
        }
        Task task = taskRepo.findById(id).get();
        task.setCompleted(true);
        taskRepo.saveAndFlush(task);
        return true;
    }

    public Task GetSingleTask(int id) throws TaskException {
        if(!taskRepo.existsById(id)){
            throw new TaskException(Errors.TASK_NOT_FOUND);
        }
        return taskRepo.findById(id).get();
    }
}
