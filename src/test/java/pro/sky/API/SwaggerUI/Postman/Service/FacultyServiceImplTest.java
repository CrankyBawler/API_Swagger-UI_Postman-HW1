package pro.sky.API.SwaggerUI.Postman.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Repository.FacultyRepository;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceImplTest {
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private FacultyServiceImpl facultyServiceImpl;
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    public void beforeEach() {
        facultyServiceImpl = new FacultyServiceImpl(facultyRepository);

    }

    @Test
    void addFacultyTest() {
       
        Mockito.when(facultyRepository.save(new Faculty(1, "Name", "Color"))).thenReturn(new Faculty(1, "Name", "Color"));
        Faculty expected = new Faculty(1, "Name", "Color");

        Faculty actual = facultyServiceImpl.addFaculty(new Faculty(1, "Name", "Color"));

       assertEquals(expected, actual);

    }

    @Test
    void findFaculty() {
    }

    @Test
    void editFaculty() {
    }

    @Test
    void deleteFaculty() {
    }
}