package pro.sky.API.SwaggerUI.Postman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection <Faculty> findAllByNameOrColorIgnoreCase(String name, String color);




}
