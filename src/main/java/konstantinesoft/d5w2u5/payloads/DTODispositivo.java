package konstantinesoft.d5w2u5.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTODispositivo {
    private String tipo;
    private String stato;
    private String emailDipendente;
}
