package konstantinesoft.d5w2u5.controllers;

import konstantinesoft.d5w2u5.entities.Dipendente;
import konstantinesoft.d5w2u5.exceptions.DipendenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import konstantinesoft.d5w2u5.DAO.DipendenteDAO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteDAO dipendenteRepository;

    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        List<Dipendente> result = dipendenteRepository.findAll();
        if (result.isEmpty()) {
            throw new DipendenteNotFoundException("Nessun dipendente trovato");
        }
        return result;
    }

    @GetMapping("/{email}")
    public Dipendente getByEmail(@PathVariable String email) {
        return dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non trovato"));
    }

    @PostMapping
    public Dipendente addDipendente(@RequestBody Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }
}
