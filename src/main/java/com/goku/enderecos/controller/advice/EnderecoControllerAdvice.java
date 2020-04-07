package com.goku.enderecos.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goku.enderecos.builder.ErrorResponseBuilder;
import com.goku.enderecos.exception.EnderecoNotFoundException;
import com.goku.enderecos.response.ErrorResponse;

@RestControllerAdvice
public class EnderecoControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(EnderecoControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {

		log.error(exception.getMessage(), exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponseBuilder().message(exception.getMessage()).build());
	}

	@ExceptionHandler(EnderecoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEnderecoNotFoundException(
			EnderecoNotFoundException enderecoNotFoundException) {
		log.error(enderecoNotFoundException.getMessage(), enderecoNotFoundException);

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponseBuilder().message(enderecoNotFoundException.getMessage()).build());
	}

}
