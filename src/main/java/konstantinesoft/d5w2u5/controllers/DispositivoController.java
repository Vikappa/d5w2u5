package konstantinesoft.d5w2u5.controllers;
import konstantinesoft.d5w2u5.DAO.DispositivoDAO;
import konstantinesoft.d5w2u5.DAO.DipendenteDAO;
import konstantinesoft.d5w2u5.entities.Dispositivo;
import konstantinesoft.d5w2u5.entities.Dipendente;
import konstantinesoft.d5w2u5.exceptions.DipendenteNotFoundException;
import konstantinesoft.d5w2u5.payloads.DTODispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

    @Autowired
    private DispositivoDAO dispositivoRepository;

    @Autowired
    private DipendenteDAO dipendenteRepository;

    // POST {baseUrl}/dispositivi?email={email}
    @PostMapping
    public ResponseEntity<Dispositivo> addDispositivo(@RequestBody Dispositivo dispositivo, @RequestParam(required = false) String email) {
        if (email != null && !email.isEmpty()) {
            Dipendente dipendente = dipendenteRepository.findByEmail(email)
                    .orElseThrow(() -> new DipendenteNotFoundException("Dipendente con email " + email + " non trovato"));
            dispositivo.setDipendente(dipendente);
        }

        Dispositivo savedDispositivo = dispositivoRepository.save(dispositivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDispositivo);
    }

    @PostMapping("/{email}")
    public ResponseEntity<Dispositivo> addDispositivoWithDipendente(
            @PathVariable String email,
            @RequestBody DTODispositivo dto) {

        Dipendente dipendente = dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente not found with email: " + email));

        Dispositivo newDispositivo = new Dispositivo();
        newDispositivo.setTipo(dto.getTipo());
        newDispositivo.setStato(dto.getStato());
        newDispositivo.setDipendente(dipendente);

        Dispositivo savedDispositivo = dispositivoRepository.save(newDispositivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDispositivo);
    }


    @GetMapping
    public ResponseEntity<List<Dispositivo>> getAllDispositiviWithDipendenti() {
        List<Dispositivo> dispositivi = dispositivoRepository.findAllWithDipendenteEager();
        if (dispositivi.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dispositivi);
    }
}

