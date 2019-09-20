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
	
	public static FinancialTransaction startNewFinancialTransaction() {
		return BeanUtil.getBean(FinancialTransaction.class);
	}
	
	public static void commitFinancialTransaction(FinancialTransaction financialTransaction) {
		financialTransaction.commitTransaction();
	}

}
