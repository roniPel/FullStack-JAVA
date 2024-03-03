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



    //List<Coupon> findByCustomer_Id(int customerId);

    /*
    SELECT * FROM coupons
    JOIN customers_vs_coupons
    ON coupons.id = customers_vs_coupons.coupons_id
    WHERE customer_id = ?
     */
    @Query(value = "select avg(weight) from cats",nativeQuery = true)
    double avg();

    @Transactional
    void deleteByCompanyId(int companyId);

    void deleteByCustomers_id(int customerId);


    /*
    List findByLearner_guid(String learnerGuid);

    The  findBy  method allows traversal through the related tables and their fields,
    starting from the ProductUsage relation to Learner. By using "_", the framework can
    clearly indicate a query by joining the Learner table where guid = ?.
    In all other cases, the framework attempts the following two combinations:

    where learnerGuid=?
    join learner where guid=?
     */

    // Todo - how to solve?  Add query? Add a Repository Adapter? Use "_"?
    List<Coupon> findByCustomers_id(int customerId);
    List<Coupon> findByCategoryAndCustomers_id(Category category, int customerId);
    List<Coupon> findByPriceLessThanEqualAndCustomers_id(double price, int customerId);
}