package br.edu.ifsp.manhani.massoterapia.exception;

import java.util.Optional;

import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private IMessageProperty message;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(IMessageProperty message) {
		super(message.message());
		this.message = message;
	}

	public ResourceNotFoundException(IMessageProperty message, Throwable cause) {
		super(message.message(), cause);
		this.message = message;
	}

	public Optional<IMessageProperty> getMessageField() {
		return Optional.ofNullable(message);
	}

}
