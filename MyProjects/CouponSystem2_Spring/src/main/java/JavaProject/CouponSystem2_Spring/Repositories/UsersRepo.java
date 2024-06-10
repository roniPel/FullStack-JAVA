package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<UserDetails,Integer> {
    UserDetails findByEmailAndPassword(String email, String password);
    void deleteByEmailAndPassword(String email, String password);
    UserDetails findByEmail(String email);
}
