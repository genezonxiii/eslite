package tw.com.pershing.error;

public final class CuslsDetailAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CuslsDetailAlreadyExistException(final String message) {
        super(message);
    }
	
}
