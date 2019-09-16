/**
 * 
 */
package com.shubhanuj.springboot.student.model;

import java.math.BigDecimal;

/**
 * @author Shubhanuj
 *
 */
public class Money {
	
	private BigDecimal value;
	private String currency;
	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
