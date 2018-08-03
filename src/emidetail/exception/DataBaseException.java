package emidetail.exception;

/**
 * Class <code>ServerException</code> stored all error on this application
 * @author Denis_Vitovtov
 *
 */
public class DataBaseException extends Exception{
	
	/**
	 * Detail of error
	 */
	private String message;

	/**
	 * Creating new ServerException
	 * @param message 
	 */
	public DataBaseException(String message) {
		this.message = message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return message;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return message;
		
	}

}
