package pro.sky.API.SwaggerUI.Postman.Service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.API.SwaggerUI.Postman.Model.Avatar;

import java.io.IOException;

public interface AvatarService {

    Avatar findAvatar(Long studentId);

    String getExtensions(String fileName);

    void uploadAvatarFromNetwork(Long studentId, String path, String avatarName) throws IOException;

    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

}
