package SpringPractice.HW_Ex2.repositories;

import SpringPractice.HW_Ex2.Beans.Lecturer;
import SpringPractice.HW_Ex2.Beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer,Integer> {
    List<Lecturer> findByName(String name);
}
