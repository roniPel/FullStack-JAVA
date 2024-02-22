package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //Todo - Add coupon expiration as query (10%).  Almost all other - CRUD (50%)
    //Todo - Add REST to Spring section
    //Todo - each controller will have its own advice
    //Todo - don't use try/catch, use controller advice messages (see github)
}