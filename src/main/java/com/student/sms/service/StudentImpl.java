package com.student.sms.service;

import com.student.sms.dto.StudentDto;
import com.student.sms.entity.Student;
import com.student.sms.mapper.StudentMapper;
import com.student.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {

        Student student = StudentMapper.mapToStudent(studentDto);

        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }
}