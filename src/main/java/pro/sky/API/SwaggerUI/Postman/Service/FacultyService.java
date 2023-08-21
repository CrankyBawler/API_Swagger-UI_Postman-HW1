package pro.sky.API.SwaggerUI.Postman.Service;

import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;

import java.util.Collection;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> findByNameOrColor(String name, String color);

    Faculty get(long facultyId);

    public Faculty getLongFacultyName();
}
