package com.JohnBryce.Exam130324.Controllers;

import com.JohnBryce.Exam130324.Beans.Student;
import com.JohnBryce.Exam130324.Exceptions.SchoolSystemException;
import com.JohnBryce.Exam130324.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/School")
public class StudentController implements StudentService{
    @Autowired
    StudentService studentService;

    @Override
    @PostMapping(value = {"/AddStudent"})
    @ResponseStatus(HttpStatus.CREATED)
    public void AddStudent(@Validated @RequestBody Student student) throws SchoolSystemException {
        studentService.AddStudent(student);
    }

    @Override
    @DeleteMapping("/DeleteStudent/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteStudent(@PathVariable Long id) throws SchoolSystemException {
        studentService.DeleteStudent(id);
    }

    @Override
    @GetMapping(value = {"/GetAllStudents"})
    public List<Student> GetAllStudents(){
        return studentService.GetAllStudents();
    }

    @Override
    @GetMapping(value = {"/GetOneStudent/{id}"})
    public Student GetOneStudent(@PathVariable Long id) throws SchoolSystemException {
        return studentService.GetOneStudent(id);
    }

    @Override
    @GetMapping(value = {"/GetStudentByName/{name}"})
    public List<Student> GetStudentByName(@PathVariable String name) {
        return studentService.GetStudentByName(name);
    }

    @Override
    @GetMapping(value = {"/GetStudentAverage/{id}"})
    public double GetStudentAverage(@PathVariable Long id) throws SchoolSystemException {
        return studentService.GetStudentAverage(id);
    }
}
