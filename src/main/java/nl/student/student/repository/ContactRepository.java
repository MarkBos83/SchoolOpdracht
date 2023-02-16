package nl.student.student.repository;

import nl.student.student.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ContactRepository extends CrudRepository<Contact, Long> {
    Iterable<Contact> findByName(String name);
}
