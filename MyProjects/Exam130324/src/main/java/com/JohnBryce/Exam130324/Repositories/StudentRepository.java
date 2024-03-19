package com.JohnBryce.Exam130324.Repositories;

import com.JohnBryce.Exam130324.Beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    @Query(value = "SELECT avg(score) FROM grades g JOIN students_grades svg ON svg.grades_id = g.id WHERE svg.student_id = ?",
            nativeQuery = true)
    Double findAverageStudentGrade(Long studentId);
}
