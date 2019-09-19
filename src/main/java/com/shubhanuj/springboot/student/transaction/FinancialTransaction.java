/**
 * 
 */
package com.shubhanuj.springboot.student.transaction;

import org.springframework.beans.factory.annotation.Autowired;

import com.shubhanuj.springboot.student.model.PaymentTransaction;
import com.shubhanuj.springboot.student.service.TransactionService;

/**
 * This class is used to record every new financial transaction that occurs with the wallet.
 * @author Shubhanuj
 *
 */
public class FinancialTransaction {
	
	@Autowired
	private PaymentTransaction paymentTransaction;
	
	@Autowired
	TransactionService transactionService;

	/**
	 * @return the paymentTransaction
	 */
	public PaymentTransaction getPaymentTransaction() {
		return this.paymentTransaction;
	}

	public void commitTransaction() {
		transactionService.saveTransaction(this.paymentTransaction);
	}
		
	

}
