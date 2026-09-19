package com.bomboniere.estoque.exception;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {
 private ResponseEntity<ApiError> resposta(HttpStatus status, String mensagem) { return ResponseEntity.status(status).body(new ApiError(status.value(), status.getReasonPhrase(), mensagem, java.time.LocalDateTime.now())); }
 @ExceptionHandler(RecursoNaoEncontradoException.class) ResponseEntity<ApiError> naoEncontrado(RuntimeException e){ return resposta(HttpStatus.NOT_FOUND,e.getMessage()); }
 @ExceptionHandler({ConflitoException.class, EstoqueInsuficienteException.class}) ResponseEntity<ApiError> conflito(RuntimeException e){ return resposta(HttpStatus.CONFLICT,e.getMessage()); }
 @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, IllegalArgumentException.class}) ResponseEntity<ApiError> invalido(Exception e) { String msg=e instanceof MethodArgumentNotValidException ex ? ex.getBindingResult().getFieldError().getDefaultMessage() : "Requisição inválida"; return resposta(HttpStatus.BAD_REQUEST,msg); }
}
