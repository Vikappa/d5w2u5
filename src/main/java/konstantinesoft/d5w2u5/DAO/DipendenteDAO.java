package konstantinesoft.d5w2u5.DAO;

import jakarta.transaction.Transactional;
import konstantinesoft.d5w2u5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, String> {
    Optional<Dipendente> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    List<Dipendente> findByNomeContainingIgnoreCase(String nome);

    List<Dipendente> findByCognomeContainingIgnoreCase(String cognome);
}
