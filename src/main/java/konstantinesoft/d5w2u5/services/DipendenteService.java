package konstantinesoft.d5w2u5.services;

import konstantinesoft.d5w2u5.DAO.DipendenteDAO;
import konstantinesoft.d5w2u5.entities.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteDAO dipendenteRepository;

    public Dipendente getDipendenteByEmail(String email) {
        return dipendenteRepository.findByEmail(email);
    }

    public void addDipendente(Dipendente dipendente) {
        dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(String email) {
        dipendenteRepository.deleteByEmail(email);
    }

    public void updateDipendente(Dipendente dipendente) {
        dipendenteRepository.save(dipendente);
    }

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public List<Dipendente> getDipendentiByNome(String nome) {
        return dipendenteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Dipendente> getDipendentiByCognome(String cognome) {
        return dipendenteRepository.findByCognomeContainingIgnoreCase(cognome);
    }
}

