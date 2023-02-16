package nl.student.student.repository;

import nl.student.student.model.Contact;
import nl.student.student.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> findByName(String name);
}
