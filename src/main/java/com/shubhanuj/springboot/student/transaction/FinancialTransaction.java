/**
 * 
 */
package com.shubhanuj.springboot.student.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shubhanuj.springboot.student.model.PaymentTransaction;
import com.shubhanuj.springboot.student.service.TransactionService;

/**
 * This class is used to record every new financial transaction that occurs with the wallet.
 * @author Shubhanuj
 *
 */
@Component
public class FinancialTransaction {
	
	@Autowired
	TransactionService transactionService;
	
	private void init() {
		if(this.paymentTransaction==null)
			this.paymentTransaction=new PaymentTransaction();
		
	}

	private PaymentTransaction paymentTransaction;
	
	/**
	 * @return the paymentTransaction
	 */
	public PaymentTransaction getPaymentTransaction() {
		init();
		return this.paymentTransaction;
	}

	public void commitTransaction() {
		transactionService.saveTransaction(this.paymentTransaction);
	}
		
	

}
