package tw.com.pershing.error;

public final class CuslsAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CuslsAlreadyExistException(final String message) {
        super(message);
    }
	
}
