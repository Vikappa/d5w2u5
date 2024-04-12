package konstantinesoft.d5w2u5.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dipendente_email")
    private Dipendente dipendente;
}

