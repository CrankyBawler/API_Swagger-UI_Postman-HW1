package pro.sky.API.SwaggerUI.Postman;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@OpenAPIDefinition
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
