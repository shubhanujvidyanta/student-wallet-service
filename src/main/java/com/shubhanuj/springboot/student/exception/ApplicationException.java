/**
 * 
 */
package com.shubhanuj.springboot.student.exception;

/**
 * @author Shubhanuj
 *
 */
public class ApplicationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3145475228334713847L;


	public ApplicationException() {
		super();
	}
	
	
	public ApplicationException(String message) {
		super(message);
	}
}
