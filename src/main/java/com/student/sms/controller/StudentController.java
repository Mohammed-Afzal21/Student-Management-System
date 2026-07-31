package com.student.sms.controller;

import com.student.sms.dto.StudentDto;
import com.student.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new StudentDto());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "create_student";
        }

        studentService.createStudent(studentDto);
        return "redirect:/students";
    }

    // used to edit the student
    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        StudentDto studentDto = studentService.getStudentById(id);
        model.addAttribute("student", studentDto);
        return "edit_student";
    }

    //used to update or save the student
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            studentDto.setId(id);
            model.addAttribute("student", studentDto);
            return "edit_student";
        }

        studentDto.setId(id);
        studentService.updateStudent(studentDto);

        return "redirect:/students";
    }
    //used to delete the student
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}