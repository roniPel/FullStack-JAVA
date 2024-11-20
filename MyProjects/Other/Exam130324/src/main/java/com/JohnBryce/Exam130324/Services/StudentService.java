package com.JohnBryce.Exam130324.Services;

import com.JohnBryce.Exam130324.Beans.Student;
import com.JohnBryce.Exam130324.Exceptions.SchoolSystemException;

import java.util.List;

public interface StudentService {
    void AddStudent(Student student) throws SchoolSystemException;
    void DeleteStudent(Long id) throws SchoolSystemException;
    List<Student> GetAllStudents();
    Student GetOneStudent(Long id) throws SchoolSystemException;
    List<Student> GetStudentByName(String name);
    double GetStudentAverage(Long id) throws SchoolSystemException;
}
