package fla.vitorcandido.restaurantreservation.exception;

import fla.vitorcandido.restaurantreservation.entity.exception.BadRequestException;
import fla.vitorcandido.restaurantreservation.entity.exception.ConflictException;
import fla.vitorcandido.restaurantreservation.entity.ResponseMessage;
import fla.vitorcandido.restaurantreservation.entity.exception.NotFoundException;
import fla.vitorcandido.restaurantreservation.entity.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationException> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(
                new ValidationException("Missing or invalid fields values", errors),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseMessage> badRequestExceptionHandler(BadRequestException exception) {
        return new ResponseEntity<>(
                new ResponseMessage(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseMessage> conflictExceptionHandler(ConflictException exception) {
        return new ResponseEntity<>(
                new ResponseMessage(exception.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> notFoundExceptionHandler(NotFoundException exception) {
        return new ResponseEntity<>(
                new ResponseMessage(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

}
