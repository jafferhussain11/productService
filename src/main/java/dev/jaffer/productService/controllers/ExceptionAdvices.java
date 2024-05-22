package dev.jaffer.productService.controllers;

import dev.jaffer.productService.dtos.ErrorResponseDto;
import dev.jaffer.productService.exceptions.NotFoundExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundExceptions.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundExceptions exception) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setErrorMessage(exception.getMessage());
        return ResponseEntity.status(404).body(responseDto);
    }
}
