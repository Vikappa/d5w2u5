package konstantinesoft.d5w2u5.DAO;

import konstantinesoft.d5w2u5.entities.Dispositivo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispositivoDAO extends JpaRepository<Dispositivo, Integer> {

    @EntityGraph(attributePaths = {"dipendente"})
    List<Dispositivo> findAllWithDipendenteEager();
}
