package pro.sky.API.SwaggerUI.Postman.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for add student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

   public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
       logger.info("Was invoked method for findByAgeBetween");
       return studentRepository.findByAgeBetween(ageMin,ageMax);
   }


    public Student get(long studentId) {
        logger.info("Was invoked method for get");
        return studentRepository.getById(studentId);
    }

    public List<Integer> getQuantityOfAllStudents() {
        logger.info("Was invoked method for getQuantityOfAllStudents");
        return studentRepository.getQuantityOfAllStudents();
    }

    public List<Double> getAverageAge() {
        logger.info("Was invoked method for getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<Student> getFiveLastStudents() {
        logger.info("Was invoked method for getFiveLastStudents");
        return studentRepository.getFiveLastStudents();
    }

    public List<Student> getStudentsByName(String name) {
        logger.info("Was invoked method for getStudentsByName");
        return studentRepository.getStudentsByName(name);
    }

    public List<Student> getStudentsNamesLetterA() {
        return studentRepository.findAll().stream()
                .map(student -> new Student(student.getId(),
                        StringUtils.capitalize(student.getName()),
                        student.getAge()))
                .filter(student -> student.getName().startsWith("А"))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
    public double getMiddleAgesStudents() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0d);
    }


}
