/**
 * 
 */
package com.shubhanuj.springboot.student.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shubhanuj.springboot.student.model.PaymentTransaction;
import com.shubhanuj.springboot.student.service.TransactionService;

/**
 * This class is used to record every new financial transaction that occurs with the wallet.
 * @author Shubhanuj
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FinancialTransaction {
	
	private final static int SUCCESSFUL_TRANSACTION_STATUS=2;
	private final static int FAILURE_TRANSACTION_STATUS=3;
	private final static int ON_HOLD_TRANSACTION_STATUS=4;
	
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
	
	public void setPaymentTransactionForTransactionId(Long transactionId) {
		this.paymentTransaction=transactionService.getTransactionById(transactionId).get();
	}

	public Long commitTransaction() {
		
		return transactionService.saveTransaction(this.paymentTransaction).getId();
	}
	
	public int getSuccessfulTransactionStatus() {
		return FinancialTransaction.SUCCESSFUL_TRANSACTION_STATUS;
	}
	
	public int getFailedTransactionStatus() {
		return FinancialTransaction.FAILURE_TRANSACTION_STATUS;
	}
	
	public int getOnHoldTransactionStatus() {
		return FinancialTransaction.ON_HOLD_TRANSACTION_STATUS;
	}
		
	

}
