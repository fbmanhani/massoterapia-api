package br.edu.ifsp.manhani.massoterapia.exception;

import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;

public class ErrorMessage {
	private String error;
	private String message;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(IMessageProperty messageProperty) {
		this.error = messageProperty.key();
		this.message = messageProperty.message();
	}

	public ErrorMessage(String error, String message) {
		this.error = error;
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
