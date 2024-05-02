package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Credentials,Integer> {
    Credentials findByUserEmailAndUserPassword(String userEmail, String userPassword);
    void deleteByUserEmailAndUserPassword(String userEmail, String userPassword);
}
