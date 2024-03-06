package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Company Repository - used to connect to the DB and perform actions relevant to companies
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByName(String name);
    boolean existsCompanyByEmail(String email);
    Company findByEmail(String email);
    Company findByEmailAndPassword(String email, String password);
}
