package com.example.hwExamPractice_Spring.Repositories;

import com.example.hwExamPractice_Spring.Beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByIsCompletedTrue();
    List<Task> findByIsCompletedFalse();
    List<Task> findByDateCompletedBefore(LocalDate dateCompleted);
}
