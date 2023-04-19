package tingeso.mingeso.pep1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubirDataProcentajeRepository extends JpaRepository <SubirDataPorcentajeEntity, String> {

}
