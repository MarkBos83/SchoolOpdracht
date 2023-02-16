package nl.student.student.controller;

import nl.student.student.model.Contact;
import nl.student.student.model.Student;
import nl.student.student.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/{studentid}/new")
    public Contact addContact (@PathVariable(value = "studentid") long studentid, @RequestBody Contact contact){
        return contactService.addContact(contact, studentid);
    }

    @GetMapping("/all")
    public Iterable<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Optional<Contact> getContactById(@PathVariable(value = "id") long id){
        return contactService.getContactById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllContacts(){
        contactService.deleteAllContacts();
    }

    @DeleteMapping("/{id}")
    public String deleteContactById(@PathVariable(value = "id") long id){
        return contactService.deleteContactById(id);
    }

    @PutMapping("/{id}")
    public Contact updateContactById(@PathVariable(value = "id") long id, @RequestBody Contact contact){
        return contactService.updateContactById(id, contact);
    }

    @GetMapping("/name/{name}")
    public Iterable<Contact> getContactByName(@PathVariable(value = "name") String name) {
        return contactService.getContactByName(name);
    }
}
