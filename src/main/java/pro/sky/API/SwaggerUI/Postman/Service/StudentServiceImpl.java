package pro.sky.API.SwaggerUI.Postman.Service;

import org.springframework.stereotype.Service;
import pro.sky.API.SwaggerUI.Postman.Entity.FiveLastStudents;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {

        studentRepository.deleteById(id);
    }

   public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
       return studentRepository.findByAgeBetween(ageMin,ageMax);
   }


    public Student get(long studentId) {
        return studentRepository.getById(studentId);
    }

    public List<Integer> getQuantityOfAllStudents() {
        return studentRepository.getQuantityOfAllStudents();
    }

    public List<Double> getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<Student> getFiveLastStudents() {
        return studentRepository.getFiveLastStudents();
    }

}
