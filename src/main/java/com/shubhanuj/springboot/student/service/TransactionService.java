package com.shubhanuj.springboot.student.service;

import java.util.Optional;

import com.shubhanuj.springboot.student.model.PaymentTransaction;

public interface TransactionService {
	
	public PaymentTransaction saveTransaction(PaymentTransaction paymentTransaction);
	
	public Optional<PaymentTransaction> getTransactionById(Long transactionId);

}
