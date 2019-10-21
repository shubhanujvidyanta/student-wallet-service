package com.shubhanuj.springboot.student.service;

import java.util.Map;

import com.shubhanuj.springboot.student.model.Money;
import com.shubhanuj.springboot.student.model.Wallet;

public interface WalletService {


	public Wallet saveWallet(Wallet wallet);

	public Map<String, Object> addMoneyToStudentWalletFromExternalPayment(Long studentId, Money money);

	public Map<String, Object> getWalletMapForStudent(Long studentId);
	
	public Map<String, Object> putWalletBalanceOnHold(Long studentId, Money money);
	
	public Map<String, Object> depositOnHoldBalance(Long transactionId, Money money);

}