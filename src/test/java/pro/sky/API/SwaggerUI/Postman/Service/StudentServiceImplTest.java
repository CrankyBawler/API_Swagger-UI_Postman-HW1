package pro.sky.API.SwaggerUI.Postman.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;

    private StudentServiceImpl studentServiceimpl;

    @BeforeEach
    public void init() {
        studentServiceimpl = new StudentServiceImpl(studentRepository);
    }

    @Test
    void addStudent() {
        Student student = new Student(1, "Name", "Age");
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Student expexted = student;
        Student actual = studentServiceimpl.addStudent(student);

        assertEquals(expexted, actual);

        Mockito.verify(studentRepository).save(student);
    }

    @Test
    void findStudent() {
        Student student = new Student(1, "Name", "Age");
        Mockito.when(studentRepository.getById(Mockito.any())).thenReturn(student);
        Student expected = student;
        Student actual = studentServiceimpl.findStudent(1);

        assertEquals(expected, actual);

        Mockito.verify(studentRepository).getById(student.getId());
    }

    @Test
    void editStudent() {
        Student student = new Student(1, "Name", "Age");
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Student expexted = student;
        Student actual = studentServiceimpl.editStudent(student);

        Mockito.verify(studentRepository).save(student);


    }

    @Test
    void deleteStudent() {
        Student student = new Student(1, "Name", "Age");
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Student student1 = studentServiceimpl.addStudent(student);
        studentServiceimpl.deleteStudent(1);
        Mockito.verify(studentRepository).deleteById(student1.getId());
    }
}