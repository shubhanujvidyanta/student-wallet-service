/**
 * 
 */
package com.shubhanuj.springboot.student.exception;

/**
 * @author Shubhanuj
 *
 */
public class SystemException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2432557007446234255L;

	public SystemException() {
		super();
	}
	
	public SystemException(String exp) {
		super(exp);
	}
}
