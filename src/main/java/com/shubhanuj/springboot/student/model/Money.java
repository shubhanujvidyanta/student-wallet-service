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
	
	/**
	 * @param value
	 * @param currency
	 */
	public Money(BigDecimal value, String currency) {
		super();
		this.value = value;
		this.currency = currency;
	}
	
	
	/**
	 * 
	 */
	public Money() {
		super();
	}


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
	public Money setValue(BigDecimal value) {
		this.value = value;
		return this;
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
	public Money setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
