/**
 * 
 */
package com.shubhanuj.springboot.student.constants;

import java.math.BigDecimal;

/**
 * @author Shubhanuj
 *
 */
public class WalletConstants {
	
	private static final int WALLET_LIMIT_VALUE=5000;
	public static final BigDecimal WALLET_LIMIT= new BigDecimal(WALLET_LIMIT_VALUE) ;
	public static final String CURRENCY_INDIA="INR";
	
	public enum WALLET_AVAILABLE_CURRENCY {
		INDIA ("INR");
		
		private String CURRENCY;
		
		private WALLET_AVAILABLE_CURRENCY(String currency) {
			this.setCURRENCY(currency);
		}

		/**
		 * @return the CURRENCY
		 */
		public String getCURRENCY() {
			return this.CURRENCY;
		}

		/**
		 * @param CURRENCY the CURRENCY to set
		 */
		private void setCURRENCY(String CURRENCY) {
			this.CURRENCY = CURRENCY;
		}
	}

}
