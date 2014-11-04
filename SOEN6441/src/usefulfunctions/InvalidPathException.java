package usefulfunctions;

/**
 * This defined exception class indicates an invalidate path error occurs.
 * 
 * @author Yichen LI
 *
 */

public class InvalidPathException extends Exception {

	/**
	 * Default constructor.
	 */
	public InvalidPathException() {
		
	}

	
	/**
	 * Passes error string message.
	 * 
	 * @param message
	 */
	public InvalidPathException(String message) {
		
		super(message);
		
	}

}
