package fla.vitorcandido.restaurantreservation.entity.exception;

import java.util.Map;

public class ValidationException {
    public String message;
    public Map<String, String> errors;

    public ValidationException(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
