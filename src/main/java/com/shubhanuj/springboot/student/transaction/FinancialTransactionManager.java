/**
 * 
 */
package com.shubhanuj.springboot.student.transaction;

import com.shubhanuj.springboot.student.utils.BeanUtil;

/**
 * @author Shubhanuj
 *
 */

public class FinancialTransactionManager {
	
	private static FinancialTransaction financialTrans;
	
	public static FinancialTransaction startNewFinancialTransaction() {
		financialTrans=BeanUtil.getBean(FinancialTransaction.class);
		System.out.println(financialTrans.hashCode());
		return financialTrans;
	}
	
	private static Long commitFinancialTransaction(FinancialTransaction financialTransaction) {
		return financialTransaction.commitTransaction();
	}
	
	public static Long commitSuccessfulTransaction(FinancialTransaction financialTransaction) {
		financialTransaction.getPaymentTransaction().setTransactionStatus(financialTrans.getSuccessfulTransactionStatus());
		return commitFinancialTransaction(financialTransaction);
	}
	
	public static Long commitFailedTransaction(FinancialTransaction financialTransaction) {
		financialTransaction.getPaymentTransaction().setTransactionStatus(financialTrans.getFailedTransactionStatus());
		return commitFinancialTransaction(financialTransaction);
	}
	
	public static Long commitOnHoldTransaction(FinancialTransaction financialTransaction) {
		financialTransaction.getPaymentTransaction().setTransactionStatus(financialTrans.getOnHoldTransactionStatus());
		return commitFinancialTransaction(financialTransaction);
	}

}
