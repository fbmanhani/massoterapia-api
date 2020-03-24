package br.edu.ifsp.manhani.massoterapia.exception;

import java.util.Optional;

import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;

public class InfraException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private IMessageProperty message = null;

	public InfraException() {
        super();
	}

	public InfraException(Throwable cause) {
		super(cause);
	}

	public InfraException(IMessageProperty message) {
		super(message.message());
		this.message = message;
	}

	public InfraException(IMessageProperty message, Throwable cause) {
		super(message.message(), cause);
		this.message = message;
	}

	public Optional<IMessageProperty> getMessageField() {
		return Optional.ofNullable(message);
	}

}
