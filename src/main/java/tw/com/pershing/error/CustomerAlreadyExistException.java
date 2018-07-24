package tw.com.pershing.error;

public final class CustomerAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistException(final String message) {
        super(message);
    }
	
}
