package pro.sky.API.SwaggerUI.Postman.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.API.SwaggerUI.Postman.Model.Faculty;
import pro.sky.API.SwaggerUI.Postman.Model.Student;
import pro.sky.API.SwaggerUI.Postman.Repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
                this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for addFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for findFaculty");
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByNameOrColor(String name, String color) {
        logger.info("Was invoked method for findByNameOrColor");
        return facultyRepository.findAllByNameOrColorIgnoreCase(name, color);
    }

    public Faculty get(long facultyId) {
        logger.info("Was invoked method for get");
        return facultyRepository.getById(facultyId);
    }

    public Faculty getLongFacultyName() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparing(faculty -> faculty.getName().length()))
                .orElse(null);
    }





}
