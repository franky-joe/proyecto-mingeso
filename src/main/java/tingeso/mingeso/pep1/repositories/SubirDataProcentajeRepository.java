package tingeso.mingeso.pep1.repositories;

import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubirDataProcentajeRepository extends JpaRepository <SubirDataPorcentajeEntity, String> {
}
