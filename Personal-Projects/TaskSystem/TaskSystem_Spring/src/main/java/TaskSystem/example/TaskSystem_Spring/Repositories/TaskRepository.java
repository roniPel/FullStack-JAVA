package TaskSystem.example.TaskSystem_Spring.Repositories;

import TaskSystem.example.TaskSystem_Spring.Beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
