package nl.student.student.controller;

import nl.student.student.model.Contact;
import nl.student.student.model.Course;
import nl.student.student.model.School;
import nl.student.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/all")
    public Iterable<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") long id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCourses(){
        courseService.deleteAllCourses();
    }

    @DeleteMapping("/{id}")
    public String deleteCourseById(@PathVariable(value = "id") long id){
        return courseService.deleteCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourseById(@PathVariable(value = "id") long id, @RequestBody Course course){
        return courseService.updateCourseById(id, course);
    }

    @PostMapping("/new")
    public Course addCourse (@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/name/{name}")
    public Iterable<Course> getCourseByName(@PathVariable(value = "name") String name) {
        return courseService.getCourseByName(name);
    }
}
