package tw.com.pershing.error;

public final class ItemAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistException(final String message) {
        super(message);
    }
	
}
