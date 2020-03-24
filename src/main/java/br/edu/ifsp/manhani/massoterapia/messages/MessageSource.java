package br.edu.ifsp.manhani.massoterapia.messages;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSource {
	private static final ResourceBundleMessageSource MESSAGE_SOURCE;

	static {
		MESSAGE_SOURCE = new ResourceBundleMessageSource();
		MESSAGE_SOURCE.setBasenames("properties/messages", "api/messages");
		MESSAGE_SOURCE.setDefaultEncoding("UTF-8");
	}

	private static MessageSource instancia;

	private MessageSource() {
		super();
	}

	public static MessageSource get() {
		if (instancia == null) {
			instancia = new MessageSource();
		}
		return instancia;
	}

	public String message(String key) {
		return MESSAGE_SOURCE.getMessage(key, null, Locale.getDefault());
	}

	public String message(String key, String... args) {
		return MESSAGE_SOURCE.getMessage(key, args, Locale.getDefault());
	}
}
