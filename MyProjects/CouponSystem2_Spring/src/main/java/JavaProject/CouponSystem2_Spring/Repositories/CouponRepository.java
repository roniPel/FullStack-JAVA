package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Coupon> findByCompany_idAndCategory(int company_id, Category category);
    List<Coupon> findByCompany_idAndPriceLowerThan(int company_id, double price);
    List<Coupon> findByCompany_id(int company_id);
    Coupon findByTitleAndCompany_id(String title, int company_id);

    // Add a Repository Adapter
    List<Coupon> findByCustomer_id(int customer_id);
    List<Coupon> findByCustomer_idAndCategory(int customer_id, Category category);
    List<Coupon> findByCustomer_idAndPriceLowerThan(int customer_id, double price);

}