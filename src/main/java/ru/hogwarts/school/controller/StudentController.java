package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }
    @PutMapping("/edit")
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
    @DeleteMapping("delete/{id}")
    public void deleteStudent(@PathVariable long id) {
        service.deleteStudent(id);
    }
    @GetMapping("sorted/{age}")
    public Collection<Student> studentsTheSameAge(@PathVariable int age) {
        return service.allStudents().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
    @GetMapping("studentsByAgeBetween")
    public Collection<Student> allStudentsByAgeBetween(@RequestParam int min,
                                                       @RequestParam int max) {
        return service.allStudentsByAgeBetween(min, max);
    }
    @GetMapping("getStudentFaculty")
    public Faculty getStudentFaculty(@PathVariable Student student) {
        return service.getStudentFaculty(student);
    }

}
