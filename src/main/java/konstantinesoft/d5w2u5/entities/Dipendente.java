package konstantinesoft.d5w2u5.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dispositivo> dispositivi;
}