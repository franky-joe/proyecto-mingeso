package tingeso.mingeso.pep1;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tingeso.mingeso.pep1.entities.ReporteEntity;
import tingeso.mingeso.pep1.repositories.ReporteRepository;
import tingeso.mingeso.pep1.services.ReporteService;


@SpringBootApplication
public class Pep1Application {
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(Pep1Application.class, args);
	}

}
