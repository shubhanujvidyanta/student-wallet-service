/**
 * 
 */
package com.shubhanuj.springboot.student.transaction;

/**
 * @author Shubhanuj
 *
 */
public class TransactionManager {
	
	
	public static FinancialTransaction startFinancialTransaction() {
		return new FinancialTransaction();
	}
	
	public static void commitFinancialTransaction(FinancialTransaction financialTransaction) {
		financialTransaction.commitTransaction();
	}

}
