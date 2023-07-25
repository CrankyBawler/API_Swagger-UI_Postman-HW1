package pro.sky.API.SwaggerUI.Postman.Service;

import pro.sky.API.SwaggerUI.Postman.Model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> findByNameOrColor(String name, String color);
}
