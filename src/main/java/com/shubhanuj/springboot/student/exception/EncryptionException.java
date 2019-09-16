/**
 * 
 */
package com.shubhanuj.springboot.student.exception;

/**
 * @author Shubhanuj
 *
 */
public class EncryptionException extends SystemException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 666197337426380957L;

	public EncryptionException() {
		super();
	}
	
	public EncryptionException(String exp) {
		super(exp);
	}

}
