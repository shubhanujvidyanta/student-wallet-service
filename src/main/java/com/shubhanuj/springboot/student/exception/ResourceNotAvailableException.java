/**
 * 
 */
package com.shubhanuj.springboot.student.exception;

/**
 * @author Shubhanuj
 *
 */
public class ResourceNotAvailableException extends SystemException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1358544437087175514L;
	
	public ResourceNotAvailableException() {
		super();
	}
	
	public ResourceNotAvailableException(String message) {
		super(message);
	}

}
