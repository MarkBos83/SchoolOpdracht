package nl.student.student.controller;

import nl.student.student.model.Contact;
import nl.student.student.model.School;
import nl.student.student.model.Student;
import nl.student.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public Iterable<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable(value = "id") long id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllStudents(){
        studentService.deleteAllStudents();
    }

    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable(value = "id") long id){
        return studentService.deleteStudentById(id);
    }

    @PostMapping("/new")
    public Student addStudent (@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable(value = "id") long id, @RequestBody Student student){
        return studentService.updateStudentById(id, student);
    }

    @PutMapping("/{studentid}/{courseid}")
    public Student addCourseToStudent(@PathVariable(value = "studentid") long studentid, @PathVariable(value = "courseid") long courseid){
        return studentService.addCourseToStudent(studentid, courseid);
    }

    @GetMapping("/name/{name}")
    public Iterable<Student> getStudentByName(@PathVariable(value = "name") String name) {
        return studentService.getStudentByName(name);
    }
}
