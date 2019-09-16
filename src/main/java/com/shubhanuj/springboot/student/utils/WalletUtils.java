/**
 * 
 */
package com.shubhanuj.springboot.student.utils;

import com.shubhanuj.springboot.student.model.Money;

/**
 * @author Shubhanuj
 *
 */


public class WalletUtils {
	
	/**
	 * This method will convert the passed Money object to the given currency
	 * @param currency
	 * @param money
	 * @return convertedMoney
	 */
	public static Money convertCurrency(String currency, Money money) {
		
		
		Money convertedMoney=new Money();
		 // write code to convert money
		convertedMoney.setValue(money.getValue());
		convertedMoney.setCurrency(currency);
		
		return convertedMoney;
	}
	
	

}
