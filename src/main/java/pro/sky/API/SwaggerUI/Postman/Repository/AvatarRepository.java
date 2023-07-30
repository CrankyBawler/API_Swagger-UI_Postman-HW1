package pro.sky.API.SwaggerUI.Postman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.API.SwaggerUI.Postman.Model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {

    Optional<Avatar> findAvatarByStudentId(Long studentId);
}
