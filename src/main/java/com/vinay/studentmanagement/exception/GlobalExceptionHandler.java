package com.vinay.studentmanagement.exception;

import java.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vinay.studentmanagement.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		
		return ResponseEntity
				.badRequest()
				.body(buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		
		String message = ex.getBindingResult()
				.getFieldErrors()
	            .stream()
	            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	            .collect(Collectors.joining(", "));
		
		return ResponseEntity
				.badRequest()
				.body(buildErrorResponse(HttpStatus.BAD_REQUEST, message));
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		return ResponseEntity
				.badRequest()
				.body(buildErrorResponse(HttpStatus.BAD_REQUEST, "Email already exists. Please use a different email"));
	}
	
	private ErrorResponse buildErrorResponse(HttpStatus status, String message) {
		ErrorResponse error = new ErrorResponse();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(status.value());
		error.setError(status.getReasonPhrase());
		error.setMessage(message);
		
		return error;
	}
	
}
