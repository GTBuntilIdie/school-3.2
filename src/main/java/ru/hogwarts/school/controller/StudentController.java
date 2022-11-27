package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = service.editStudent(student);
        if (editStudent == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editStudent);
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student findStudent = service.findStudent(id);
        if (findStudent == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findStudent);
    }
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable long id) {
        return service.deleteStudent(id);
    }
    @GetMapping("/age/{age}")
    public List<Student> studentsTheSameAge(@PathVariable int age) {
        return service.getStudents().values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

}
