package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();

    public HashMap<Long, Faculty> getFaculties() {
        return faculties;
    }

    private long count = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }
    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }
    public Faculty editFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            return null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }
    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

}
