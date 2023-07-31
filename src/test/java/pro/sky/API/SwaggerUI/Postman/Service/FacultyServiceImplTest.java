package pro.sky.API.SwaggerUI.Postman.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Repository.FacultyRepository;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration (classes = {FacultyServiceImpl.class})
@ExtendWith({MockitoExtension.class})
class FacultyServiceImplTest {
    @Mock
    private FacultyRepository facultyRepository;

    private FacultyServiceImpl facultyServiceImpl;


    @BeforeEach
    public void init() {
        facultyServiceImpl = new FacultyServiceImpl(facultyRepository);
    }
    @Test
    void addFacultyTest() {
        // Подготовка данных
        Faculty faculty = new Faculty(1, "Name", "Color");
        Mockito.when(facultyRepository.save(Mockito.any(Faculty.class))).thenReturn(faculty);
        // Выполнение
        Faculty expected = faculty;
        Faculty actual = facultyServiceImpl.addFaculty(faculty);
        // Проверка
        assertEquals(expected, actual);

        Mockito.verify(facultyRepository).save(faculty);


   }

    @Test
    void findFaculty() {
        Faculty faculty = new Faculty(1, "Name", "Color");
        Mockito.when(facultyRepository.getById(Mockito.any())).thenReturn(faculty);
        Faculty expected = faculty;
        Faculty actual = facultyServiceImpl.findFaculty(1);

        assertEquals(expected, actual);

        Mockito.verify(facultyRepository).getById(faculty.getId());

    }


    @Test
    void editFaculty() {
        Faculty faculty = new Faculty(1, "Name", "Color");
        Mockito.when(facultyRepository.save(Mockito.any(Faculty.class))).thenReturn(faculty);
        Faculty expected = faculty;
        Faculty actual = facultyServiceImpl.editFaculty(faculty);

        assertEquals(expected, actual);

        Mockito.verify(facultyRepository).save(faculty);
    }

    @Test
    void deleteFaculty() {
        Faculty faculty = new Faculty(1, "Name", "Color");
        Mockito.when(facultyRepository.save(Mockito.any(Faculty.class))).thenReturn(faculty);
        Faculty faculty1 = facultyServiceImpl.addFaculty(faculty);
        facultyServiceImpl.deleteFaculty(1);

        Mockito.verify(facultyRepository).deleteById(faculty1.getId());

    }
}