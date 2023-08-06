package pro.sky.API.SwaggerUI.Postman.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Arrays;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.API.SwaggerUI.Postman.Controller.FacultyController;
import pro.sky.API.SwaggerUI.Postman.Controller.StudentController;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.AvatarRepository;
import pro.sky.API.SwaggerUI.Postman.Repository.FacultyRepository;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ApplicationTestWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private StudentService studentService;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private StudentController studentController;
    @InjectMocks
    private FacultyController facultyController;
   @Test
    void createStudentTest() throws Exception {
       Long id = 1L;
       String name = "Вася";
       int age = 28;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("Age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void createFacultyTest() throws Exception {
        Long id = 1L;
        String name = "Факультет 12";
        String color = "Бурый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    void editStudentTest() throws Exception {
        Long id = 1L;
        String name = "Миша";
        int age = 25;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student(id, name, age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void editFacultyTest() throws Exception {
        Long id = 1L;
        String name = "Факультет 14";
        String color = "Салатовый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    void getStudentInfoTest() throws Exception {
        Long id = 1L;
        String name = "Ярослав";
        int age = 19;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student(id, name, age);

       when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void getFacultyInfoTest() throws Exception {
        Long id = 1L;
        String name = "Факультет 15";
        String color = "Серый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    void deleteStudentTest() throws Exception {
        Long id = 1L;
        String name = "Кирилл";
        int age = 26;

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student(id, name, age);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFacultyTest() throws Exception {
        Long id = 1L;
        String name = "Факультет 15";
        String color = "Серый";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty(id, name, color);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

       @Test
    void byAgeBetweenTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", "");
        studentObject.put("age", "");

        Student student = new Student(1L, "Ира", 6);
        Student student2 = new Student(2L, "Валера", 12);
           List<Student> list = new ArrayList<>();
           list.add(student);
           list.add(student2);


        when(studentRepository.findByAgeBetween(any(Integer.class),any(Integer.class))).thenReturn(list);
           ObjectMapper objectMapper = new ObjectMapper();
           mockMvc.perform(MockMvcRequestBuilders
                           .get("/student/byAgeBetween?minAge=5&maxAge=13")
                           .content(studentObject.toString())
                           .contentType(MediaType.APPLICATION_JSON)
                           .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect((ResultMatcher) content().json(objectMapper.writeValueAsString(Arrays.asList(list))));

    }
}

