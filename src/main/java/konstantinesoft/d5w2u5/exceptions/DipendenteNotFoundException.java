package konstantinesoft.d5w2u5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DipendenteNotFoundException extends RuntimeException {

    public DipendenteNotFoundException(String message) {
        super(message);
    }

}
