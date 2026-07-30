package com.student.sms.controller;

import com.student.sms.dto.StudentDto;
import com.student.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Display all students
    @GetMapping("/students")
    public String listStudents(Model model) {

        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);

        return "students";
    }

    // Display Add Student form
    @GetMapping("/students/new")
    public String newStudent(Model model) {

        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);

        return "create_student";
    }
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") StudentDto studentDto) {
        studentService.createStudent(studentDto);
        return "redirect:/students";
    }
}