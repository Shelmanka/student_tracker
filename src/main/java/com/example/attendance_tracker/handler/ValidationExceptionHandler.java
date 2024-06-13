package com.example.attendance_tracker.handler;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ValidationExceptionHandler implements Serializable {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, Locale locale){
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundServiceException.class})
    @ResponseBody
    protected ResponseEntity<?> handleNotFoundServiceException(NotFoundServiceException e, Locale locale){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    protected ResponseEntity<?> handleServiceException(ServiceException e, Locale locale){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
