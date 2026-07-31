package com.student.sms.service;

import com.student.sms.dto.StudentDto;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    void createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

    void deleteStudent(Long id);

    void updateStudent(@Valid StudentDto studentDto);
}
