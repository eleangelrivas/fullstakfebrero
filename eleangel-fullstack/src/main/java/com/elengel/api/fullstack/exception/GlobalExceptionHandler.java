package com.elengel.api.fullstack.exception;

import com.elengel.api.fullstack.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice//mapean errores
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception exception, HttpServletRequest request){

        ApiError error = new ApiError();
        error.setMessage("Error interno en el servidor, vuelva a intentarlo");
        error.setBackedMessage(exception.getLocalizedMessage());
        error.setTime(LocalDateTime.now());
        error.setHttpCode(500);

        error.setUrl(request.getRequestURL().toString());
        error.setMethod(request.getMethod());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){

        ApiError error = new ApiError();
        error.setMessage("Error: La petición enviada no es correcta, por favor verifique la estructura de campos obligatorios");
        error.setBackedMessage(exception.getLocalizedMessage());
        error.setTime(LocalDateTime.now());
        error.setHttpCode(400);

        error.setUrl(request.getRequestURL().toString());
        error.setMethod(request.getMethod());



        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
