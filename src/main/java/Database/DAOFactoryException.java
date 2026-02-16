package Database;

public class DAOFactoryException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DAOFactoryException( String message ) {
        super( message );
    }

    public DAOFactoryException( String message, Throwable cause ) {
        super( message, cause );
    }
}
