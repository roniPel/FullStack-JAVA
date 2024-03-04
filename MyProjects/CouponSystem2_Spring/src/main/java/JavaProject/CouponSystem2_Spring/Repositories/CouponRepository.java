package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //Todo - Add coupon expiration as SQL query (10%).  Almost all other - CRUD (50%)
    // Go over methods below and update based on CRUD
    //Todo - Add REST to Spring section
    //Todo - each controller will have its own advice
    //Todo - don't use try/catch, use controller advice messages (see github)

    // Company Smart Dialect Queries
    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, double price);
    List<Coupon> findByCompanyId(int companyId);
    Coupon findByTitleAndCompanyId(String title, int companyId);

    @Transactional
    void deleteByCompanyId(int companyId);

    // Todo - Ask Zeev if my solution is correct:
    //      convert coupons List to 'set'(in 'company' class)
    //      and create 'coupons' set inside 'customer class - ?

    // Customer Smart Dialect Queries
    List<Coupon> findByCustomers_id(int customerId);
    List<Coupon> findByCategoryAndCustomers_id(Category category, int customerId);
    List<Coupon> findByPriceLessThanEqualAndCustomers_id(double price, int customerId);

    // Expired Coupons Job
    @Query(value = "SELECT * FROM coupons WHERE end_date <= DATE(NOW())", nativeQuery = true)
    List<Coupon> GetExpiredCoupons();

}