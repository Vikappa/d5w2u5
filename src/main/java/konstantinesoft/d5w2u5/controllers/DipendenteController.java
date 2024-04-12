package konstantinesoft.d5w2u5.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import konstantinesoft.d5w2u5.entities.Dipendente;
import konstantinesoft.d5w2u5.exceptions.DipendenteNotFoundException;
import konstantinesoft.d5w2u5.exceptions.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import konstantinesoft.d5w2u5.DAO.DipendenteDAO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Autowired
    private DipendenteDAO dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

     //GET {baseUrl}/dipendenti
    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        List<Dipendente> result = dipendenteRepository.findAll();
        if (result.isEmpty()) {
            throw new DipendenteNotFoundException("Nessun dipendente trovato");
        }
        return result;
    }

     // GET {baseUrl}/dipendenti/{email}
    @GetMapping("/{email}")
    public Dipendente getByEmail(@PathVariable String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Email non valida: " + email);
        }
        return dipendenteRepository.findByEmail(email)
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente con email " + email + " non trovato"));
    }

    //POST {baseUrl}/dipendenti
    @PostMapping
    public Dipendente addDipendente(@RequestBody Dipendente dipendente) {
        if (!EMAIL_PATTERN.matcher(dipendente.getEmail()).matches()) {
            throw new InvalidEmailException("Email non valida: " + dipendente.getEmail());
        }
        return dipendenteRepository.save(dipendente);
    }
    // PUT /dipendenti/{email}/avatar
    @PutMapping("/{email}/avatar")
    public Dipendente updateDipendenteAvatar(@PathVariable String email, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String newAvatarUrl = (String) uploadResult.get("url");

            Dipendente dipendente = dipendenteRepository.findByEmail(email)
                    .orElseThrow(() -> new DipendenteNotFoundException("Dipendente con email " + email + " non trovato"));

            dipendente.setAvatar(newAvatarUrl);
            return dipendenteRepository.save(dipendente);
        } else {
            throw new IllegalArgumentException("File non presente");
        }
    }

}
