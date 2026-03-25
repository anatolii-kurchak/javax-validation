package com.levi.demo.utils;

import com.levi.javax_validation.model.Error;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String STATUS_CODE = "statusCode";

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<List<Error>> handleConstraintViolationException(ConstraintViolationException e) {
        HttpStatus httpStatus = getHttpStatus(e.getConstraintViolations());

        List<Error> errors = createErrors(e.getConstraintViolations());

        return ResponseEntity.status(httpStatus).body(errors);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Set<ConstraintViolation<?>> constraintViolations = extractConstraintViolations(e);

        HttpStatus httpStatus = getHttpStatus(constraintViolations);

        List<Error> errors = createErrors(constraintViolations);

        return ResponseEntity.status(httpStatus).body(errors);
    }

    private List<Error> createErrors(Collection<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
            .map(violation -> new Error()
                .message(violation.getMessage())
                .field(violation.getPropertyPath().toString())
                .date(OffsetDateTime.now()))
            .collect(Collectors.toList());
    }

    private Set<ConstraintViolation<?>> extractConstraintViolations(MethodArgumentNotValidException e) {
        return e.getAllErrors().stream()
            .map(error -> error.unwrap(ConstraintViolation.class))
            .map(v -> (ConstraintViolation<?>)v)
            .collect(Collectors.toSet());
    }

    private HttpStatus getHttpStatus(Collection<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
            .map(violation -> violation.getConstraintDescriptor().getAttributes().get(STATUS_CODE))
            .filter(Objects::nonNull)
            .map(StatusCode.class::cast)
            .distinct()
            .findFirst()
            .orElse(StatusCode.BAD_REQUEST)
            .getHttpStatus();
    }

}
