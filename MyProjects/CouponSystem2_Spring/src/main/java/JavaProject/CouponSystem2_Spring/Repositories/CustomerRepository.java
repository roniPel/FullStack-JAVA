package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer Repository - used to connect to the DB and perform actions relevant to customers
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
}
