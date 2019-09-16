/**
 * 
 */
package com.shubhanuj.springboot.student.exception;

/**
 * @author Shubhanuj
 *
 */
public class CreateResourceException extends SystemException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7558245866610178078L;
	
	public CreateResourceException() {
		super();
	}
	
	public CreateResourceException(String message) {
		super(message);
	}

}
