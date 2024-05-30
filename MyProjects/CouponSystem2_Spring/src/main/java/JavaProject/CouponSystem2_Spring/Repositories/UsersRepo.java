package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<UserDetails,Integer> {
    UserDetails findByUserEmailAndUserPassword(String userEmail, String userPassword);
    void deleteByUserEmailAndUserPassword(String userEmail, String userPassword);
}
