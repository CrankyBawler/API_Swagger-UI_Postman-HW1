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

//   @Test
//   public void editStudentTest() {
//    Student student = new Student(findLastStudentId(), "Имя2", 20);
//       this.testRestTemplate.put("http://localhost:" + port + "/student", student);
//       Optional<Student> optionalStudent = studentRepository.findById(findLastStudentId());
//
//       assertTrue(optionalStudent.isPresent());
//
//
//       Student actualStudent = optionalStudent.get();
//       assertEquals(student, actualStudent);
//   }

//   @Test
//   public void editFacultyTest() {
//         Faculty faculty = new Faculty(findLastFacultyId(), "Факультет2", "Белый");
//       ResponseEntity<Faculty> response = facultyController.editFaculty(faculty);
//       int actualStatusCodeValue = response.getStatusCodeValue();
//       int expectedCode = 200;
//       Assertions.assertEquals(expectedCode, actualStatusCodeValue, "коды не совпадают");
//   }

   @Test
   void deleteStudentTest() {
       Student lastStudent = studentRepository.findById(findLastStudentId()).orElse(null);
       Long lastStudentId = (lastStudent == null) ? null : lastStudent.getId();
       this.testRestTemplate.delete("http://localhost:" + port + "/student/" + findLastStudentId());

       assertNotEquals(findLastStudentId(), lastStudentId);
   }

//   /**
//    * тестируем удаление факультета
//    */
//   @Test
//   void deleteFacultyTest() {
//       Faculty lastFaculty = facultyRepository.findById(findLastFacultyId()).orElse(null);
//       Long lastFacultyId = (lastFaculty == null) ? null : lastFaculty.getId();

//       this.testRestTemplate.delete("http://localhost:" + port + "/faculty/" + findLastFacultyId());

//       assertNotEquals(findLastFacultyId(), lastFacultyId);
//   }

//   /**
//    * тестируем получение всех студентов
//    */
//   @Test
//   void getAllStudentsTest() {
//       //создание заголовков
//       HttpHeaders headers = new HttpHeaders();
///        headers.set("accept", "application/json");
///        headers.set("Authorization", "Bearer JWT TOKEN HERE");
//       HttpEntity requestEntity = new HttpEntity<>(null, headers);
//       //создание запроса через метод exchange
//       ResponseEntity<List<Student>> response = testRestTemplate.exchange(
//               "http://localhost:" + port + "/student", HttpMethod.GET, requestEntity,
//               new ParameterizedTypeReference<List<Student>>() {
//               });
//       //получение списка студентов из тела запроса
//       List<Student> students = response.getBody();
//       System.out.println("students = " + students);
//       assertNotNull(students);
//   }

//   /**
//    * тестируем получение всех факультетов
//    */
//   @Test
//   void getAllFacultyTest() {
//       //создание заголовков
//       HttpHeaders headers = new HttpHeaders();
///        headers.set("accept", "application/json");
///        headers.set("Authorization", "Bearer JWT TOKEN HERE");
//       HttpEntity requestEntity = new HttpEntity<>(null, headers);
//       //создание запроса через метод exchange
//       ResponseEntity<List<Faculty>> response = testRestTemplate.exchange(
//               "http://localhost:" + port + "/faculty", HttpMethod.GET, requestEntity,
//               new ParameterizedTypeReference<List<Faculty>>() {
//               });
//       //получение списка студентов из тела запроса
//       List<Faculty> faculties = response.getBody();
//       System.out.println("faculties = " + faculties);
//       assertNotNull(faculties);
//   }

//   /**
//    * тестируем получение студентов по возрасту
//    */
//   @Test
//   void getStudentsAccordingAge() {
//       //создаю студента
//       int studentsAge = 25;
//       studentRepository.save(new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAge));
//       long studentId = findLastStudentId();

//       //в блок try оборачиваю конструкцию, чтобы студент в блоке finally гарантированно удалялся.
//       try {
//           assertNotNull(this.testRestTemplate.getForObject(
//                   "http://localhost:" + port + "/student/filter_by_age/" + studentsAge, String.class));
//       } catch (Exception e) {
//           throw new RuntimeException(e);
//       } finally {
//           studentRepository.deleteById(studentId);
//       }
//   }

//   /**
//    * тестируем получение факультетов по цвету
//    */
//   @Test
//   void getFacultyAccordingColor() {
//       //создаю студента
//       String facultyColor = "малиновый";
//       facultyRepository.save(new Faculty(findLastFacultyId() + 1, "тестовыйФакультет", facultyColor));
//       long facultyId = findLastFacultyId();

//       //в блок try оборачиваю конструкцию, чтобы факультет в блоке finally гарантированно удалялся.
//       try {
//           assertNotNull(this.testRestTemplate.getForObject(
//                   "http://localhost:" + port + "/faculty/filter_by_color/?color=" + facultyColor, String.class));
//       } catch (Exception e) {
//           throw new RuntimeException(e);
//       } finally {
//           facultyRepository.deleteById(facultyId);
//       }
//   }

//   /**
//    * тестируем получение студентов по возрасту между мин и макс
//    */
//   @Test
//   void findStudentByAgeBetween() {
//       int studentsAgeMin = 25;
//       int studentsAgeMax = 55;

//       studentRepository.save(new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAgeMin));
//       long studentIdMin = findLastStudentId();

//       studentRepository.save(new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAgeMax));
//       long studentIdMax = findLastStudentId();

//       try {
//           assertNotNull(this.testRestTemplate.getForObject(
//                   "http://localhost:" + port + "/student/find_age_between/?minAge=" + studentsAgeMin +
//                           "&maxAge=" + studentsAgeMax, String.class));
//       } catch (Exception e) {
//           throw new RuntimeException(e);
//       } finally {
//           System.out.println("studentIdMin  studentIdMax = " + studentIdMin + " " + studentIdMax);
//           studentRepository.deleteById(findLastStudentId());
//           studentRepository.deleteById(findLastStudentId());
//       }
//   }
//   /**
//    * тестируем поиск студента по факультету
//    */
//   @Test
//   void findStudentByFaculty() {
//       int studentsAge = 25;
//       Student student = new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAge);
//       studentRepository.save(student);

//       try {
//           assertNotNull(this.testRestTemplate.
//                   getForObject(
//                           "http://localhost:" + port + "/student/find_student_by_faculty", String.class, student));
//       } catch (Exception e) {
//           throw new RuntimeException(e);
//       } finally {
//           studentRepository.deleteById(findLastStudentId());
//       }
//   }
//   /**
//    * тестируем поиск факультета по студенту
//    */
//   @Test
//   void findFacultyByStudent() {
//       Faculty faculty = new Faculty(findLastFacultyId() + 1, "тестовыйФакультет", "красный");
//       facultyRepository.save(faculty);

//       try {
//           assertNotNull(this.testRestTemplate.
//                   getForObject(
//                           "http://localhost:" + port + "/student/find_faculty_by_student", String.class, faculty));
//       } catch (Exception e) {
//           throw new RuntimeException(e);
//       } finally {
//           facultyRepository.deleteById(findLastFacultyId());
//       }
//   }
}
