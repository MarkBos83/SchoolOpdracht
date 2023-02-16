package nl.student.student.repository;

import nl.student.student.model.Contact;
import nl.student.student.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findByName(String name);
}
