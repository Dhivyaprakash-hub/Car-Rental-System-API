package com.carrentalsystem.api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice public class ApiErrors {
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,Object> invalid(MethodArgumentNotValidException ex){return Map.of("error","Request validation failed","fields",ex.getBindingResult().getFieldErrors().stream().map(e->Map.of("field",e.getField(),"message",e.getDefaultMessage()==null?"invalid":e.getDefaultMessage())).toList());}
}
