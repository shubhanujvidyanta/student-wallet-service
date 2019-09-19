package com.shubhanuj.springboot.student.service;

import java.util.Map;

import com.shubhanuj.springboot.student.model.Money;
import com.shubhanuj.springboot.student.model.Wallet;

public interface WalletService {


	Map<String, Object> getWalletForStudent(Long studentId);

	Wallet saveWallet(Wallet wallet);

	Map<String, Object> addMoneyToStudentWalletFromExternalPayment(Long studentId, Money money);

}