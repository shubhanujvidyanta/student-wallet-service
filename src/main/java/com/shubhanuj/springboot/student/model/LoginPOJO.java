/**
 * 
 */
package com.shubhanuj.springboot.student.model;

import com.shubhanuj.springboot.student.exception.EncryptionException;
import com.shubhanuj.springboot.student.utils.StudentUtils;

/**
 * @author Shubhanuj
 *
 */
public class LoginPOJO {
	
	private String email;
	private String password;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	protected String getPassword() {
		return password;
	}
	
	public String getEncryptedPassword() throws EncryptionException {
		return StudentUtils.encryptPassword(getPassword());
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
