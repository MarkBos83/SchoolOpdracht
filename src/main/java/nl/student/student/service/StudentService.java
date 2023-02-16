package nl.student.student.service;

import jakarta.transaction.Transactional;
import nl.student.student.model.Contact;
import nl.student.student.model.Course;
import nl.student.student.model.School;
import nl.student.student.model.Student;
import nl.student.student.repository.ContactRepository;
import nl.student.student.repository.CourseRepository;
import nl.student.student.repository.SchoolRepository;
import nl.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CourseRepository courseRepository;

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
        contactRepository.deleteAll();
        Iterable<School> schools = schoolRepository.findAll();
        for (School school : schools) {
            school.setNumberOfStudents(0);
            System.out.println("working");
        }
        schoolRepository.saveAll(schools);
    }

    public String deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            return "student doesn't exist.";
        }
        Student student = studentRepository.findById(id).get();
        School school = student.getSchool();
        if(student.getContact()!=null) {
            Contact contact = student.getContact();
            contactRepository.delete(contact);
        }
        school.setNumberOfStudents(school.getNumberOfStudents()-1);
        studentRepository.deleteById(id);
        return "student deleted.";
    }

    public Student updateStudentById(long id, Student student) {
        if(!studentRepository.existsById(id)){
            return null;
        }
        Student oldStudent = studentRepository.findById(id).get();
        if(student.getName()!=null){
            oldStudent.setName(student.getName());
        }
        if(student.getGrade()!=null){
            oldStudent.setGrade(student.getGrade());
        }
        return studentRepository.save(oldStudent);
    }

    public Student addContact(Contact contact, Student student){
        student.setContact(contact);
        return studentRepository.save(student);
    }

    public Student addCourseToStudent(long studentid, long courseid) {
        if(!studentRepository.existsById(studentid) || !courseRepository.existsById(courseid)){
            return null;
        }
        Student student = studentRepository.findById(studentid).get();
        Course course = courseRepository.findById(courseid).get();
        student.getChosenCourses().add(course);
        course.getStudent().add(student);
        courseRepository.save(course);
        return studentRepository.save(student);
    }

    public Iterable<Student> getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}