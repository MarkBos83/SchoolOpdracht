package nl.student.student.service;

import jakarta.transaction.Transactional;
import nl.student.student.model.Contact;
import nl.student.student.model.Student;
import nl.student.student.repository.ContactRepository;
import nl.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    public Contact addContact(Contact contact, Long studentid) {
        Student student = studentRepository.findById(studentid).get();
        contact.setStudent(student);
        studentService.addContact(contact, student);
        return contactRepository.save(contact);
    }

    public Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(long id) {
        return contactRepository.findById(id);
    }

    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }

    public String deleteContactById(long id) {
        if(!contactRepository.existsById(id)){
            return "contact doesn't exist.";
        }
        contactRepository.deleteById(id);
        return "contact deleted.";
    }

    public Contact updateContactById(long id, Contact contact) {
        if(!contactRepository.existsById(id)){
            return null;
        }
        Contact oldContact = contactRepository.findById(id).get();
        if(contact.getName()!=null){
            oldContact.setName(contact.getName());
        }
        if(contact.getPhoneNumber()!=null){
            oldContact.setPhoneNumber(contact.getPhoneNumber());
        }
        if(contact.getStudent()!=null){
            oldContact.setStudent(contact.getStudent());
        }
        return contactRepository.save(oldContact);
    }

    public Iterable<Contact> getContactByName(String name) {
        return contactRepository.findByName(name);
    }
}
