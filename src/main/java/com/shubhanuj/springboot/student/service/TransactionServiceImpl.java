/**
 * 
 */
package com.shubhanuj.springboot.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhanuj.springboot.student.model.PaymentTransaction;
import com.shubhanuj.springboot.student.repository.TransactionRepository;

/**
 * @author Shubhanuj
 *
 */

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public PaymentTransaction saveTransaction(PaymentTransaction paymentTransaction) {
		return transactionRepository.save(paymentTransaction);
	}

}
