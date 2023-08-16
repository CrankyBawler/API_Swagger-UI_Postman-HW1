package pro.sky.API.SwaggerUI.Postman.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.API.SwaggerUI.Postman.Model.Avatar;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.AvatarRepository;
import pro.sky.API.SwaggerUI.Postman.Repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    public Avatar findAvatar(Long studentId) {
        logger.info("Was invoked method for findAvatar");
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }
    public String getExtensions(String fileName) {
        logger.info("Was invoked method for getExtensions");
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void uploadAvatarFromNetwork(Long studentId, String path, String avatarName) throws IOException {
        logger.info("Was invoked method for uploadAvatarFromNetwork");
        File avatarFile = new File(path);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent() && avatarFile == null) {
            logger.error("There is optionalStudent.isPresent() && avatarFile == null");
            throw new IllegalArgumentException();
        }
        Student student = optionalStudent.orElse(null);
        String originalFileName = avatarName;

        if (originalFileName == null) {
            logger.error("There is originalFileName == null");
            throw new IllegalArgumentException();
        }

        Path filePath = Path.of(path);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = Files.newInputStream(avatarFile.toPath());
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
                ){
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.length());

        String filyType = Files.probeContentType(Paths.get(String.valueOf(filePath)));
        avatar.setMediaType(filyType);

        byte[] fileBytes = Files.readAllBytes(Paths.get(String.valueOf(filePath)));
        avatar.setData(fileBytes);
        avatarRepository.save(avatar);
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked method for uploadAvatar");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent() && avatarFile == null) {
            logger.error("There is optionalStudent.isPresent() && avatarFile == null");
            throw new IllegalArgumentException();
        }
        Student student = optionalStudent.orElse(null);
        String originalFileName = avatarFile.getOriginalFilename();

        if (originalFileName == null) {
            logger.error("There is originalFileName == null");
            throw new IllegalArgumentException();
        }
        Path filePath = Path.of(avatarsDir, studentId + getExtensions(originalFileName));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);//
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for getAllAvatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }


}
