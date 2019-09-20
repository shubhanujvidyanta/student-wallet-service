/**
 * 
 */
package com.shubhanuj.springboot.student.service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhanuj.springboot.student.constants.WalletConstants;
import com.shubhanuj.springboot.student.exception.ResourceNotAvailableException;
import com.shubhanuj.springboot.student.model.Money;
import com.shubhanuj.springboot.student.model.Student;
import com.shubhanuj.springboot.student.model.Wallet;
import com.shubhanuj.springboot.student.repository.WalletRepository;
import com.shubhanuj.springboot.student.transaction.FinancialTransaction;
import com.shubhanuj.springboot.student.transaction.FinancialTransactionManager;
import com.shubhanuj.springboot.student.utils.WalletUtils;

/**
 * @author Shubhanuj
 *
 */

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	StudentService studentService;

	@Autowired
	WalletRepository walletRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shubhanuj.springboot.student.service.WalletService#
	 * addMoneyToStudentWallet(java.lang.Long,
	 * com.shubhanuj.springboot.student.model.Money)
	 */
	@Override
	public Map<String, Object> addMoneyToStudentWalletFromExternalPayment(Long studentId, Money money) {

		Map<String, Object> walletMap = new HashMap<String, Object>();
		try {
			
			walletMap.put("Wallet", addMoneyToWallet(studentId,money));
			walletMap.put("addMoneySuccess", true);
		} catch (ResourceNotAvailableException exp) {
			walletMap.put("addMoneySuccess", false);
			walletMap.put("Error", exp.getMessage());
		}

		return walletMap;
	}

	private @Valid Wallet addMoneyToWallet(Long studentId, Money money)
			throws ResourceNotAvailableException {

		if( studentId == null || money == null) {
			if(studentId == null)
				throw new ResourceNotAvailableException("Student Id passed is not available.");
			throw new ResourceNotAvailableException("Money is not available. Please check your input again.");
		}

		Wallet wallet = null;
		FinancialTransaction financialTransaction = null;
		
		try {
			
			financialTransaction = FinancialTransactionManager.startNewFinancialTransaction();
			financialTransaction.getPaymentTransaction().setMoney(money);
			financialTransaction.getPaymentTransaction().setSource(null);
			financialTransaction.getPaymentTransaction().setDestination(studentId);
			financialTransaction.getPaymentTransaction().setPaymentSystem("INTERNAL");// Not implemented yet, set internal for all
			financialTransaction.getPaymentTransaction().setTransactionStatus(1);
			financialTransaction.getPaymentTransaction().setTransactionType(1);

			Student destStudent = studentService
					.getStudentById(studentId).get();

			if (!destStudent.getWallet().getCurrency()
					.equals(financialTransaction.getPaymentTransaction().getMoney().getCurrency())) {
				financialTransaction.getPaymentTransaction()
						.setMoney(WalletUtils.convertCurrency(
								WalletConstants.WALLET_AVAILABLE_CURRENCY.INDIA.getCURRENCY(),
								financialTransaction.getPaymentTransaction().getMoney()));
			}
			destStudent.getWallet().setBalance(destStudent.getWallet().getBalance()
					.add(financialTransaction.getPaymentTransaction().getMoney().getValue()));
			destStudent.getWallet().setAvailableBalance(destStudent.getWallet().getAvailableBalance()
					.add(financialTransaction.getPaymentTransaction().getMoney().getValue()));

			wallet = saveWallet(destStudent.getWallet());

			financialTransaction.getPaymentTransaction().setTransactionStatus(2);
			FinancialTransactionManager.commitFinancialTransaction(financialTransaction);
		}
		catch (Exception exp) {
			if(financialTransaction != null)
				financialTransaction.getPaymentTransaction().setTransactionStatus(3);
			FinancialTransactionManager.commitFinancialTransaction(financialTransaction);
			
			throw new ResourceNotAvailableException(exp.getMessage());
		}
		
		
		return wallet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shubhanuj.springboot.student.service.WalletService#getWalletForStudent(
	 * java.lang.Long)
	 */
	@Override
	public Map<String, Object> getWalletMapForStudent(Long studentId) {

		Map<String, Object> walletMap = new HashMap<String, Object>();
		Student student = (Student) studentService.getStudentById(studentId).get();

		walletMap.put("Wallet", student.getWallet());

		return walletMap;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shubhanuj.springboot.student.service.WalletService#saveWallet(com.
	 * shubhanuj.springboot.student.model.Wallet)
	 */
	@Override
	public @Valid Wallet saveWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}

}
