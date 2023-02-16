package nl.student.student.repository;

import nl.student.student.model.Contact;
import nl.student.student.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SchoolRepository extends CrudRepository<School, Long> {
    Iterable<School> findByName(String name);
}
