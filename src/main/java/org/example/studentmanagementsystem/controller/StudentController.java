package org.example.studentmanagementsystem.controller;
import org.springframework.ui.Model;
import org.example.studentmanagementsystem.model.Student;
import org.example.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/register")
        public String showRegistrationForm(Model model) {
            model.addAttribute("student",new Student());
            return "register";
        }
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/student/all";
    }
    @GetMapping("/all")
    public String showAllStudents(Model model) {
        List<Student> students = studentService.findAllStudents();

        model.addAttribute("students", students);

        return "students";
    }
    }


