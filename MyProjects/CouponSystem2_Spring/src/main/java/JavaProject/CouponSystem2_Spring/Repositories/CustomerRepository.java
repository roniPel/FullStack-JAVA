package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Customer Repository - used to connect to the DB and perform actions relevant to customers
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT DISTINCT customer_id " +
            "FROM customers_vs_coupons cvc JOIN coupons coup " +
            "ON cvc.coupons_id = coup.id " +
            "WHERE coup.company_id = ?",
            nativeQuery = true)
    List<Integer> findCustomerIdByCompanyId(int companyId);
    List<Customer> findByCoupons(Coupon coupon);
}
