package Exception;

public class PanicException extends RuntimeException{
	static final long serialVersionUID = 42;
	Exception panicException;
	
	public PanicException(String message)
	{
		panicException = new Exception(message);
	}
}