package com.bfu.estoke.handler;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bfu.estoke.handler.exceptions.ExistingEmailException;
import com.bfu.estoke.handler.exceptions.InvalidCredentialsException;
import com.bfu.estoke.handler.exceptions.ProdutoNotFoundException;
import com.bfu.estoke.handler.exceptions.UserNotFoundException;
import com.bfu.estoke.handler.exceptions.EmailNotFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Value("${server.error.include-exception}")
	private Boolean printStackTrace;

	@Override
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException,
			HttpHeaders headers, HttpStatusCode status,	WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação, verifique o campo de errors para obter detalhes.");
		for (FieldError fieldError : methodArgumentNotValidException
				.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationError(fieldError.getField(),
					fieldError.getDefaultMessage());
		}
		return ResponseEntity.unprocessableEntity().body(errorResponse);
	}
	
	@ExceptionHandler(ExistingEmailException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<Object> handleExistingEmailException (
			ExistingEmailException existingEmailException, WebRequest request) {
		log.error("Esse Email já foi cadastrado", existingEmailException);
		return buildErrorResponse(existingEmailException, HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(EmailNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleUserNotFoundException (
			EmailNotFoundException emailNotFoundException, WebRequest request) {
		log.error("Email Não Encontrado", emailNotFoundException);
		return buildErrorResponse(emailNotFoundException, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleUserNotFoundException (
			UserNotFoundException userNotFoundException, WebRequest request) {
		log.error("Usuário Não Encontrado", userNotFoundException);
		return buildErrorResponse(userNotFoundException, HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleMalhasNotFoundException (
			ProdutoNotFoundException produtoNotFoundException, WebRequest request) {
		log.error("Produto Não Encontrado", produtoNotFoundException);
		return buildErrorResponse(produtoNotFoundException, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleInvalidCredentialsException (
			InvalidCredentialsException invalidCredentialsException, WebRequest request) {
		log.error("Senha Incorreta", invalidCredentialsException);
		return buildErrorResponse(invalidCredentialsException, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
	    final String errorMessage = "Ocorreu um erro desconhecido";
	    log.error(errorMessage, exception);
	    return buildErrorResponse(exception, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
	    return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus, WebRequest request) {
	    ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
	    if (this.printStackTrace) {
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        exception.printStackTrace(pw);
	        String stackTrace = sw.toString();
	        errorResponse.setStackTrace(stackTrace);
	    }
	    return ResponseEntity.status(httpStatus).body(errorResponse);
	}
}