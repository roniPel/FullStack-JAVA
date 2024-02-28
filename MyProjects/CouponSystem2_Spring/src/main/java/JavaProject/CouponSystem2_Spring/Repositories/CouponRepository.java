package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //Todo - Add coupon expiration as SQL query (10%).  Almost all other - CRUD (50%)
    // Go over methods below and update based on CRUD
    //Todo - Add REST to Spring section
    //Todo - each controller will have its own advice
    //Todo - don't use try/catch, use controller advice messages (see github)

    List<Coupon> priceLowerThan(Double maxPrice);

    //Todo - check if FindAllByCategory is correct
    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId,double price);
    List<Coupon> findByCompanyId(int companyId);
    List<Coupon> findByCustomerId(int customerId);
    List<Coupon> findByCustomerIdAndCategory(int customerId, Category category);
    List<Coupon> findByCustomerIdAndPriceLessThan(int customerId,double price);
    Coupon findByTitleAndCompanyId(String title,int id);

}