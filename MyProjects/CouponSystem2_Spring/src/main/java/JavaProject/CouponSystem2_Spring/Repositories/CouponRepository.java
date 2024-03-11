package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Coupon Repository - used to connect to the DB and perform actions relevant to coupons
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    // Company Smart Dialect Queries
    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, double price);
    List<Coupon> findByCompanyId(int companyId);
    Coupon findByTitleAndCompanyId(String title, int companyId);

    @Transactional
    void deleteByCompanyId(int companyId);

    // Expired Coupons Job
    @Query(value = "SELECT * FROM coupons WHERE end_date <= DATE(NOW())", nativeQuery = true)
    List<Coupon> GetExpiredCoupons();
}