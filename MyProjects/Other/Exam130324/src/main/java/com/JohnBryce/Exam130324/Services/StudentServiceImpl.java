package com.JohnBryce.Exam130324.Services;

import com.JohnBryce.Exam130324.Beans.Student;
import com.JohnBryce.Exam130324.Exceptions.Errors;
import com.JohnBryce.Exam130324.Exceptions.SchoolSystemException;
import com.JohnBryce.Exam130324.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepo;
    @Override
    public void AddStudent(Student student) throws SchoolSystemException {
        if(studentRepo.existsById(student.getId())) {
            throw new SchoolSystemException(Errors.ID_ALREADY_EXISTS);
        }
        studentRepo.save(student);
    }

    @Override
    public void DeleteStudent(Long id) throws SchoolSystemException {
        if(!studentRepo.existsById(id)) {
            throw new SchoolSystemException(Errors.ID_NOT_FOUND);
        }
        studentRepo.deleteById(id);
    }

    @Override
    public List<Student> GetAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student GetOneStudent(Long id) throws SchoolSystemException {
        return studentRepo.findById(id).orElseThrow(() ->
                new SchoolSystemException(Errors.ID_NOT_FOUND));
    }

    @Override
    public List<Student> GetStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    @Override
    public double GetStudentAverage(Long id) throws SchoolSystemException {
        if(!studentRepo.existsById(id)){
            throw new SchoolSystemException(Errors.ID_NOT_FOUND);
        }
        return studentRepo.findAverageStudentGrade(id);
    }
}
