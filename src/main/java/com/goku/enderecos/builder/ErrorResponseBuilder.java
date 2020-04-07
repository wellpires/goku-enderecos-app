package com.goku.enderecos.builder;

import com.goku.enderecos.response.ErrorResponse;

public class ErrorResponseBuilder {

	private String message;

	public ErrorResponseBuilder message(String message) {
		this.message = message;
		return this;
	}

	public ErrorResponse build() {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		return errorResponse;
	}

}
