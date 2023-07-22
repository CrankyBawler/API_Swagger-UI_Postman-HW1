package pro.sky.API.SwaggerUI.Postman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
