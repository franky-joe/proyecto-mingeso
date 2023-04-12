package tingeso.mingeso.pep1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso.mingeso.pep1.entities.ReporteEntity;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteEntity, Integer> {
    // A = 700
    // B = 550
    // C = 400
    // d = 250
}
