package com.example.BackEnd_Spring.Repositories;

import com.example.BackEnd_Spring.Beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
