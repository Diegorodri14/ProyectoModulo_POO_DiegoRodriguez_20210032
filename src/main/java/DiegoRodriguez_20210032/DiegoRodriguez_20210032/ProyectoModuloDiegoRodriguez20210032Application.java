package DiegoRodriguez_20210032.DiegoRodriguez_20210032;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloDiegoRodriguez20210032Application {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));


		SpringApplication.run(ProyectoModuloDiegoRodriguez20210032Application.class, args);

	}

}
