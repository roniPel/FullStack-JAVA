package JavaProject.CouponSystem2_Spring.Repositories;

import JavaProject.CouponSystem2_Spring.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    //Todo - add all CRUD actions
    Company findByName(String name);
    Company findByEmail(String email);
    Company findByEmailAndPassword(String email,String password);
}
