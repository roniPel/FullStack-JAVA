package SpringPractice.HW_Ex2.repositories;

import SpringPractice.HW_Ex2.Beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByGradeBetween(int min, int max);
    List<Student> findByEndDateBetween(Date start, Date end);
}
