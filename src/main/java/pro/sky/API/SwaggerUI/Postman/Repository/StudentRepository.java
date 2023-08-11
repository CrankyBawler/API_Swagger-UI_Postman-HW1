package pro.sky.API.SwaggerUI.Postman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.API.SwaggerUI.Postman.Entity.FiveLastStudents;
import pro.sky.API.SwaggerUI.Postman.Model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);
    @Query(value = "select count(*) from student", nativeQuery = true)
    List<Integer> getQuantityOfAllStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    List<Double> getAverageAge();

    @Query(value = "select * from student order by id desc limit 5;", nativeQuery = true)
    List<Student> getFiveLastStudents();


}
