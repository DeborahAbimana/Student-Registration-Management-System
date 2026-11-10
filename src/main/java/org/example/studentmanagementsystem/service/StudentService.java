package org.example.studentmanagementsystem.service;

import org.example.studentmanagementsystem.model.Student;
import org.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
        public void saveStudent(Student student){
            studentRepository.saveStudent(student);
        }
        public List<Student> findAllStudents() {
            return studentRepository.findAllStudents();
        }
    }

