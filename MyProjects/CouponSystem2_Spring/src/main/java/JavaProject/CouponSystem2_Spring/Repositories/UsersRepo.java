package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Credentials,Integer> {
    Credentials findByUserNameAndUserPass(String userName, String userPass);
}
