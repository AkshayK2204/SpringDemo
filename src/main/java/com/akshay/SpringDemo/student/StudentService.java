package com.akshay.SpringDemo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
        //return List.of(new Student(1L,"Akash","akash02@gmail.com", LocalDate.of(2002, Month.JANUARY,6),22));
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail  = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Duplicate email named " + student.getEmail()+ " present...");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id "+ studentId +" does not exist...");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with id "+ studentId + " does not exist"));
        if(name!=null && !name.isEmpty() && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email!=null && !email.isEmpty() && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken...");
            }
            student.setEmail(email);
        }
    }
}