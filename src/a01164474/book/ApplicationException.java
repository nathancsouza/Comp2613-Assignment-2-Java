/**
 * Project: Bcmc
 * File: ApplicationException.java
 * Date: Aug 18, 2016
 * Time: 1:59:25 PM
 */

package a01164474.book;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * Construct an ApplicationException
	 * 
	 * @param message
	 *            the exception message.
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Construct an ApplicationException
	 * 
	 * @param throwable
	 *            a Throwable.
	 */
	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

}
