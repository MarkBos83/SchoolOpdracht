package nl.student.student.controller;

import nl.student.student.model.Contact;
import nl.student.student.model.School;
import nl.student.student.model.Student;
import nl.student.student.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("/new")
    public School addSchool (@RequestBody School school){
        return schoolService.addSchool(school);
    }

    @GetMapping("/all")
    public Iterable<School> getAllSchools(){
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public Optional<School> getSchoolById(@PathVariable(value = "id") long id){
        return schoolService.getSchoolById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllSchools(){
        schoolService.deleteAllSchools();
    }

    @DeleteMapping("/{id}")
    public String deleteSchoolById(@PathVariable(value = "id") long id){
        return schoolService.deleteSchoolById(id);
    }

    @PutMapping("/{id}")
    public School updateSchoolById(@PathVariable(value = "id") long id, @RequestBody School school){
        return schoolService.updateSchoolById(id, school);
    }

    @PutMapping("/{id}/newstudent")
    public School addStudentToSchool(@PathVariable(value = "id") long id, @RequestBody Student student){
        return schoolService.addStudentToSchool(id, student);
    }

    @GetMapping("/name/{name}")
    public Iterable<School> getSchoolByName(@PathVariable(value = "name") String name) {
        return schoolService.getSchoolByName(name);
    }
}
