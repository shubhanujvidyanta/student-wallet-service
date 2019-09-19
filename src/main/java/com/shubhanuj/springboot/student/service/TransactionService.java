package com.shubhanuj.springboot.student.service;

import com.shubhanuj.springboot.student.model.PaymentTransaction;

public interface TransactionService {
	
	public PaymentTransaction saveTransaction(PaymentTransaction paymentTransaction);

}
