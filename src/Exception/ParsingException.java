package Exception;

public class ParsingException extends RuntimeException {
	static final long serialVersionUID = 42;
	Exception parsingException;

	public ParsingException(String message) {
		parsingException = new Exception(message);
	}
}