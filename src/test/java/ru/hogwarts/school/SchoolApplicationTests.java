package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	private StudentController studentController;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(studentController).isNotNull();
	}
	private Student createTestStudent(String name, int age) {
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		return student;
	}
	private ResponseEntity<Student> getCreateStudentResponse(Student student) {
		return restTemplate.postForEntity(
				"http://localhost:" + port + "/student",
				student,
				Student.class);
	}
	private void assertCreatedStudent(ResponseEntity<Student> response) {
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody()).isNotNull();
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}
	@Test
	void findStudentsTest() {
		Student student = createTestStudent("Bob", 22);
		ResponseEntity<Student> responseCreated = getCreateStudentResponse(student);
		assertCreatedStudent(responseCreated);
		Student createdStudent = responseCreated.getBody();
		ResponseEntity<Student> response = restTemplate.getForEntity(
				"http://localhost:" + port + "/student/" + createdStudent.getId(),
				Student.class);
		Assertions.assertThat(response.getBody()).isEqualTo(createdStudent);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}



}



