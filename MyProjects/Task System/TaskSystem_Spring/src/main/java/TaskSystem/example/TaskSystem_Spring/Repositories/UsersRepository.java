package TaskSystem.example.TaskSystem_Spring.Repositories;

import TaskSystem.example.TaskSystem_Spring.Beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserDetails,Integer> {
    UserDetails findByEmailAndPassword(String email, String password);
    UserDetails findByEmail(String email);
}
