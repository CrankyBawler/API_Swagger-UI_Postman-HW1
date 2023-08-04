package pro.sky.API.SwaggerUI.Postman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pro.sky.API.SwaggerUI.Postman.Controller.FacultyController;
import pro.sky.API.SwaggerUI.Postman.Controller.StudentController;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.FacultyRepository;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    private StudentController studentController;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        assertNotNull(studentController);
        assertNotNull(facultyController);
    }


   private long findLastStudentId() {
       List<Student> students = studentRepository.findAll();
       Student lastStudent = students.stream()
               .max(Comparator.comparing(Student::getId))
               .orElse(null);
       if (lastStudent == null) {
           throw new NullPointerException("таблица со студентами пуста");
       }
       return lastStudent.getId();
   }

   private long findLastFacultyId() {
       List<Faculty> faculties = facultyRepository.findAll();
       Faculty lastFaculty = faculties.stream()
               .max(Comparator.comparing(Faculty::getId))
               .orElse(null);
       if (lastFaculty == null) {
           throw new NullPointerException("таблица с факультетами пуста");
       }
       return lastFaculty.getId();
   }

   @Test
   void createStudentTest() {
       Student student = new Student(findLastStudentId() + 1, "Имя", 15);
       assertNotNull(this.testRestTemplate.postForObject(
               "http://localhost:" + port + "/student", student, String.class));
   }

   @Test
   void createFacultyTest() {
       Faculty faculty = new Faculty(findLastFacultyId() + 1, "Факультет", "Черный");
       assertNotNull(this.testRestTemplate.postForObject(
               "http://localhost:" + port + "/faculty", faculty, String.class));
   }

   @Test
   void getStudentInfoTest() {
       assertNotNull(this.testRestTemplate.getForObject(
               "http://localhost:" + port + "/student/1", String.class));
   }


   @Test
   void getFacultyInfoTest() {
       assertNotNull(this.testRestTemplate.getForObject(
               "http://localhost:" + port + "/faculty/1", String.class));
   }

   @Test
   public void editStudentTest() {
       long lastStudentId = findLastStudentId();
       Student student = new Student(lastStudentId, "Имя2", 20);
       this.testRestTemplate.put("http://localhost:" + port + "/student/" + lastStudentId, student);
       Optional<Student> optionalStudent = studentRepository.findById(lastStudentId);


       assertTrue(optionalStudent.isPresent());


       Student actualStudent = optionalStudent.get();
       assertEquals(student, actualStudent);
   }

    @Test
    public void editFacultyTest() {
        Faculty faculty = new Faculty(findLastFacultyId(), "Факультет2", "Белый");
        this.testRestTemplate.put("http://localhost:" + port + "/faculty", faculty);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(findLastFacultyId());

        assertTrue(optionalFaculty.isPresent());


        Faculty actualFaculty = optionalFaculty.get();
        assertEquals(faculty, actualFaculty);
    }

  @Test
   void deleteStudentTest() {
       Student lastStudent = studentRepository.findById(findLastStudentId()).orElse(null);
       Long lastStudentId = (lastStudent == null) ? null : lastStudent.getId();
       this.testRestTemplate.delete("http://localhost:" + port + "/student/" + findLastStudentId());

       assertNotEquals(findLastStudentId(), lastStudentId);
   }

   @Test
   void deleteFacultyTest() {
       Faculty lastFaculty = facultyRepository.findById(findLastFacultyId()).orElse(null);
       Long lastFacultyId = (lastFaculty == null) ? null : lastFaculty.getId();

       this.testRestTemplate.delete("http://localhost:" + port + "/faculty/" + findLastFacultyId());

       assertNotEquals(findLastFacultyId(), lastFacultyId);
   }

   @Test
   void findFacultyByNameOrColorTest() {
       String facultyColor = "белый";
       facultyRepository.save(new Faculty(findLastFacultyId() + 1, "Тест", facultyColor));
       long facultyId = findLastFacultyId();

       try {
           assertNotNull(this.testRestTemplate.getForObject(
                   "http://localhost:" + port + "/faculty/filter_by_color/?color=" + facultyColor, String.class));
       } catch (Exception e) {
           throw new RuntimeException(e);
       } finally {
           facultyRepository.deleteById(facultyId);
       }
   }

   @Test
   void byAgeBetweenTest() {
       int studentsAgeMin = 15;
       int studentsAgeMax = 25;

       studentRepository.save(new Student(findLastStudentId() + 1, "Ольга", studentsAgeMin));
       long studentIdMin = findLastStudentId();

       studentRepository.save(new Student(findLastStudentId() + 1, "Оля", studentsAgeMax));
       long studentIdMax = findLastStudentId();

       try {
           assertNotNull(this.testRestTemplate.getForObject(
                   "http://localhost:" + port + "/student/find_age_between/?minAge=" + studentsAgeMin +
                           "&maxAge=" + studentsAgeMax, String.class));
       } catch (Exception e) {
           throw new RuntimeException(e);
       } finally {
           System.out.println("studentIdMin  studentIdMax = " + studentIdMin + " " + studentIdMax);
           studentRepository.deleteById(findLastStudentId());
           studentRepository.deleteById(findLastStudentId());
       }
   }


   @Test
   void findStudentByFacultyTest() {

       Student student = new Student(findLastStudentId() + 1, "Вася", 20);
       studentRepository.save(student);

       try {
           assertNotNull(this.testRestTemplate.
                   getForObject(
                           "http://localhost:" + port + "/student/find_student_by_faculty", String.class, student));
       } catch (Exception e) {
           throw new RuntimeException(e);
       } finally {
           studentRepository.deleteById(findLastStudentId());
       }
   }

   @Test
   void findFacultyByStudentTest() {
       Faculty faculty = new Faculty(findLastFacultyId() + 1, "Тест", "лиловый");
       facultyRepository.save(faculty);

       try {
           assertNotNull(this.testRestTemplate.
                   getForObject(
                           "http://localhost:" + port + "/student/find_faculty_by_student", String.class, faculty));
       } catch (Exception e) {
           throw new RuntimeException(e);
       } finally {
           facultyRepository.deleteById(findLastFacultyId());
       }
   }
}
