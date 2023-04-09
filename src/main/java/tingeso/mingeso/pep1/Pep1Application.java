package tingeso.mingeso.pep1;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tingeso.mingeso.pep1.entities.EstudianteEntity;
import tingeso.mingeso.pep1.repositories.EstudianteRepository;


@SpringBootApplication
public class Pep1Application implements CommandLineRunner {
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(Pep1Application.class, args);
	}

	@Autowired
	private EstudianteRepository repositorio;

	@Override
	public void run(String... args)throws Exception{
		EstudianteEntity estudiante1, estudiante2;
		estudiante1 = new EstudianteEntity(1,"Cristian", "Ramire","peregil@gmail.com");
		estudiante2 = new EstudianteEntity(2,"Christian", "Ramera","perico@gmail.com");
		repositorio.save(estudiante1);
		repositorio.save(estudiante2);

	}

}
