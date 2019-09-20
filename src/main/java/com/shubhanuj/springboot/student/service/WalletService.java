package com.shubhanuj.springboot.student.service;

import java.util.Map;

import com.shubhanuj.springboot.student.model.Money;
import com.shubhanuj.springboot.student.model.Wallet;

public interface WalletService {


	Wallet saveWallet(Wallet wallet);

	Map<String, Object> addMoneyToStudentWalletFromExternalPayment(Long studentId, Money money);

	Map<String, Object> getWalletMapForStudent(Long studentId);

}