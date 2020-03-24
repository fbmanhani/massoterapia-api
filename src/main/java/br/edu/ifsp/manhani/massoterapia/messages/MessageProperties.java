package br.edu.ifsp.manhani.massoterapia.messages;

public enum MessageProperties implements IMessageProperty {

    EMPTY("EMPTY"),
	MSG0001("MSG0001"),
    MSG0002("MSG0002"),
    MSG0003("MSG0003"),
    MSG0004("MSG0004"),
    MSG0005("MSG0005"),
    MSG0006("MSG0006"),
    MSG0007("MSG0007"),
    MSG0008("MSG0008"),
    MSG0009("MSG0009"),
    MSG0010("MSG0010"),
    MSG0011("MSG0011"),
    MSG0012("MSG0012"),
    MSG0013("MSG0013"),
    MSG0014("MSG0014"),
    MSG0015("MSG0015"),
    MSG0016("MSG0016"),
    MSG0017("MSG0017"),
    MSG0018("MSG0018"),
    MSG0019("MSG0019"),
    MSG0020("MSG0020"),
    MSG0021("MSG0021"),
    MSG0025("MSG0025"),
    MSG0024("MSG0024"),
    MSG0026("MSG0026"),
    MSG0028("MSG0028"),
    MSG0029("MSG0029"),
    MSG0030("MSG0030"),
    MSG0032("MSG0032"),
    MSGSI001("MSGSI001"),
	MSGSI002("MSGSI002"),
	MSGSI003("MSGSI003"),
	MSGSI004("MSGSI004");

	private String[] args = {};

	private String key;

	private MessageProperties(String key) {
		this.key = key;
	}

	public String key() {
		return key;
	}

	public String message() {
		return MessageSource.get().message(key, args);
	}

	public IMessageProperty bind(String... args) {
		this.args = args;
		return this;
	}
}
