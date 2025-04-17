package pt.arnaldocanelas.projetoapi.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.AccountMovementException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.BussinessException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.DatabaseException;
import pt.arnaldocanelas.projetoapi.controllers.exceptions.ResourceNotFoundException;
import pt.arnaldocanelas.projetoapi.dto.CustomErrorDTO;
import pt.arnaldocanelas.projetoapi.dto.ValidationErrorDTO;

@ControllerAdvice
public class ControllerExceptionHandler {
	

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CustomErrorDTO> jsonParse(HttpMessageNotReadableException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		CustomErrorDTO err = new CustomErrorDTO(status.value(), e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}	
	
	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<CustomErrorDTO> bussinessLogic(BussinessException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomErrorDTO err = new CustomErrorDTO(status.value(), e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AccountMovementException.class)
	public ResponseEntity<CustomErrorDTO> accountMovement(AccountMovementException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		CustomErrorDTO err = new CustomErrorDTO(status.value(), e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<CustomErrorDTO> securityException(SecurityException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		CustomErrorDTO err = new CustomErrorDTO(status.value(), e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomErrorDTO> databaseViolations(DatabaseException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomErrorDTO err = new CustomErrorDTO(status.value(), e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomErrorDTO err = new CustomErrorDTO(status.value(), e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorDTO> validation(MethodArgumentNotValidException e, HttpServletRequest request) 
	{
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
	
}