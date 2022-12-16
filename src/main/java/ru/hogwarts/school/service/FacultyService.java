package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentService service;

    public FacultyService(FacultyRepository facultyRepository, StudentService service) {
        this.facultyRepository = facultyRepository;
        this.service = service;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
    public Faculty findFaculty(long id) {
        if (facultyRepository.findById(id).isPresent()) {
            return facultyRepository.findById(id).get();
        }
        return null;
    }
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
    public void deleteFaculty(long id) {
         facultyRepository.deleteById(id);
    }
    public Collection<Faculty> allFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findAllFacultiesByNameOrColor(String nameOrColor) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(nameOrColor, nameOrColor);
    }
    public Collection<Student> getAllFacultyStudents(Faculty faculty) {
        return service.allStudents().stream()
                .filter(student -> student.getFaculty() == faculty)
                .collect(Collectors.toList());

    }
}
