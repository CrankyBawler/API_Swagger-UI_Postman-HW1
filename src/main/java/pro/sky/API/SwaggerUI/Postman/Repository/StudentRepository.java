package pro.sky.API.SwaggerUI.Postman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.API.SwaggerUI.Postman.Model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);

}
