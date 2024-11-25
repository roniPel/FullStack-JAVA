package TaskSystem.example.TaskSystem_Spring.Repositories;

import TaskSystem.example.TaskSystem_Spring.Beans.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
