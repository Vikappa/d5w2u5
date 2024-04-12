package konstantinesoft.d5w2u5.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "stato", nullable = false)
    private String stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_email", nullable = false)
    private Dipendente dipendente;
}
