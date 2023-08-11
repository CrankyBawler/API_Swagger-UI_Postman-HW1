package pro.sky.API.SwaggerUI.Postman.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.API.SwaggerUI.Postman.Entity.FiveLastStudents;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> byAgeBetween(@RequestParam int ageMin, @RequestParam int ageMax) {
        return studentService.findByAgeBetween(ageMin, ageMax);
    }
    @GetMapping("faculty/{studentId}")
        public ResponseEntity<Faculty> getFaculty(@PathVariable Long studentId) {
        Faculty faculty = studentService.get(studentId).getFaculty();
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/get_quantity_of_all_students")
    public List<Integer> getQuantityOfAllStudents(){
        return studentService.getQuantityOfAllStudents();
    }

    @GetMapping("/get_average_age")
    public List<Double> getAverageAge(){
        return studentService.getAverageAge();
    }

    @GetMapping("/get_five_last_students")
    public List<Student> getFiveLastStudents(){
        return studentService.getFiveLastStudents();
    }

}
