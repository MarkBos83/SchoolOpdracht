package nl.student.student.service;

import jakarta.transaction.Transactional;
import nl.student.student.model.Contact;
import nl.student.student.model.School;
import nl.student.student.model.Student;
import nl.student.student.repository.SchoolRepository;
import nl.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    public Iterable<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(long id) {
        return schoolRepository.findById(id);
    }

    public void deleteAllSchools() {
        schoolRepository.deleteAll();
        studentService.deleteAllStudents();
    }

    public String deleteSchoolById(long id) {
        if(!schoolRepository.existsById(id)){
            return "school doesn't exist.";
        }
        School school = schoolRepository.findById(id).get();
        for(Student student: school.getStudents()){
            studentService.deleteStudentById(student.getId());
        }
        schoolRepository.deleteById(id);
        return "school deleted.";
    }

    public School updateSchoolById(long id, School school) {
        if(!schoolRepository.existsById(id)){
            return null;
        }
        School oldSchool = schoolRepository.findById(id).get();
        if(school.getName()!=null){
            oldSchool.setName(school.getName());
        }
        return schoolRepository.save(oldSchool);
    }

    public School addStudentToSchool(long id, Student student) {
        if(!schoolRepository.existsById(id)){
            studentService.addStudent(student);
            return null;
        }

        School school = schoolRepository.findById(id).get();
        student.setSchool(school);
        student.setSchoolname(student.getSchool().getName());
        school.getStudents().add(student);
        studentRepository.save(student);
        school.setStudents(school.getStudents());
        return schoolRepository.save(school);
    }

    public Iterable<School> getSchoolByName(String name) {
        return schoolRepository.findByName(name);
    }
}
