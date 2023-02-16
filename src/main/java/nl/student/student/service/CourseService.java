package nl.student.student.service;

import jakarta.transaction.Transactional;
import nl.student.student.model.Course;
import nl.student.student.model.School;
import nl.student.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(long id) {
        return courseRepository.findById(id);
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }

    public String deleteCourseById(long id) {
        if(!courseRepository.existsById(id)){
            return "course doesn't exist.";
        }
        courseRepository.deleteById(id);
        return "course deleted.";
    }

    public Course updateCourseById(long id, Course course) {
        if(!courseRepository.existsById(id)){
            return null;
        }
        Course oldCourse = courseRepository.findById(id).get();
        if(course.getName()!=null){
            oldCourse.setName(course.getName());
        }
        return courseRepository.save(oldCourse);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Iterable<Course> getCourseByName(String name) {
        return courseRepository.findByName(name);
    }
}
