package br.edu.ifsp.manhani.massoterapia.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<IMessageProperty> messages = new ArrayList<>();

	public BusinessException() {
		super();
	}

	public BusinessException(IMessageProperty message) {
		super(message.message());
		this.messages.add(message);
	}

	public BusinessException(IMessageProperty... messages) {
		super(Arrays.toString(messages));
		this.messages.addAll(Arrays.asList(messages));
	}

	public BusinessException(IMessageProperty message, Throwable cause) {
		super(message.message(), cause);
		this.messages.add(message);
	}

	public List<IMessageProperty> getMessages() {
		return messages;
	}

}
