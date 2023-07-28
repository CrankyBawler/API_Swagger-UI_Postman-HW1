package pro.sky.API.SwaggerUI.Postman.Service;

import pro.sky.API.SwaggerUI.Postman.Model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(Student student);

    void deleteStudent(long id);

    Collection<Student> findByAgeBetween(int ageMin, int ageMax);

    public Student get(long studentId);
}
