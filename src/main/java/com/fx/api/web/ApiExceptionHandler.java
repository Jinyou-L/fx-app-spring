package com.fx.api.web;

import com.fx.api.service.UnknownPairException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UnknownPairException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> unknown(UnknownPairException e) { return Map.of("error", e.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> invalid(MethodArgumentNotValidException e) {
        var error = e.getBindingResult().getFieldErrors().stream().findFirst();
        return Map.of("error", error.map(x -> x.getField() + ": " + x.getDefaultMessage()).orElse("invalid request"));
    }
}
