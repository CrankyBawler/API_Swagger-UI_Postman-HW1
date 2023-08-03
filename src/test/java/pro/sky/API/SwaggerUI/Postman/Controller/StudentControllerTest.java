package pro.sky.API.SwaggerUI.Postman.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pro.sky.API.SwaggerUI.Postman.Model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test

    public void getStudentInfo() throws Exception {
        Student student = new Student(0, "Name", 15);

        //Подготовка данных
        org.assertj.core.api.Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class))
                .isNotNull();

        //org.assertj.core.api.Assertions.assertThat(this.restTemplate.delete("http://localhost:" + port + "students" + student.getId(), Student.class))
        //        .isNull();

        // Выполнение
        org.assertj.core.api.Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students" + student.getId(), Student.class))
                .isEqualTo(student);

        // Проверка
    }

    @Test
    void createStudent() {
    }

    @Test
    void editStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void byAgeBetween() {
    }

    @Test
    void getFaculty() {
    }
}