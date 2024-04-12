package konstantinesoft.d5w2u5.exceptions;

import konstantinesoft.d5w2u5.controllers.DipendenteController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice(assignableTypes = DipendenteController.class)
public class DipendenteControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public String handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception ex, WebRequest request) {
        return "Errore generico CRUD dipendenti: " + ex.getMessage();
    }
}
