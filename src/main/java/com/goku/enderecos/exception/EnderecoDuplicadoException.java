package com.goku.enderecos.exception;

public class EnderecoDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 3220197739178565980L;

	public EnderecoDuplicadoException() {
		super("Endereço já existente!");
	}

}
