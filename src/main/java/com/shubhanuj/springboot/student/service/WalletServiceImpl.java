/**
 * 
 */
package com.shubhanuj.springboot.student.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhanuj.springboot.student.constants.WalletConstants;
import com.shubhanuj.springboot.student.exception.ApplicationException;
import com.shubhanuj.springboot.student.exception.ResourceNotAvailableException;
import com.shubhanuj.springboot.student.model.Money;
import com.shubhanuj.springboot.student.model.PaymentTransaction;
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

			return addMoneyToWallet(studentId, money);

		} catch (ResourceNotAvailableException exp) {
			walletMap.put("addMoneySuccess", false);
			walletMap.put("Error", exp.getMessage());
		}

		return walletMap;
	}

	private @Valid Map<String, Object> addMoneyToWallet(Long studentId, Money money)
			throws ResourceNotAvailableException {

		if (studentId == null || money == null) {
			if (studentId == null)
				throw new ResourceNotAvailableException("Student Id passed is not available.");
			throw new ResourceNotAvailableException("Money is not available. Please check your input again.");
		}

		Map<String, Object> walletMap = new HashMap<String, Object>();

		Wallet wallet = null;
		FinancialTransaction financialTransaction = null;

		financialTransaction = FinancialTransactionManager.startNewFinancialTransaction();
		financialTransaction.getPaymentTransaction().setMoney(money);
		financialTransaction.getPaymentTransaction().setSource(null);
		financialTransaction.getPaymentTransaction().setDestination(studentId);
		financialTransaction.getPaymentTransaction().setPaymentSystem("INTERNAL");// Not implemented yet, set
																					// internal for all
		financialTransaction.getPaymentTransaction().setTransactionStatus(1);
		financialTransaction.getPaymentTransaction().setTransactionType(1);

		Student destStudent = studentService.getStudentById(studentId).get();

		if (!destStudent.getWallet().getCurrency()
				.equals(financialTransaction.getPaymentTransaction().getMoney().getCurrency())) {
			financialTransaction.getPaymentTransaction()
					.setMoney(WalletUtils.convertCurrency(WalletConstants.WALLET_AVAILABLE_CURRENCY.INDIA.getCURRENCY(),
							financialTransaction.getPaymentTransaction().getMoney()));
		}
		destStudent.getWallet().setBalance(destStudent.getWallet().getBalance()
				.add(financialTransaction.getPaymentTransaction().getMoney().getValue()));
		destStudent.getWallet().setAvailableBalance(destStudent.getWallet().getAvailableBalance()
				.add(financialTransaction.getPaymentTransaction().getMoney().getValue()));

		wallet = saveWallet(destStudent.getWallet());

		walletMap.put("Wallet", wallet);
		walletMap.put("transactionId", FinancialTransactionManager.commitSuccessfulTransaction(financialTransaction));
		walletMap.put("addMoneySuccess", true);

		return walletMap;
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
		Student student = studentService.getStudentById(studentId).get();

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
	
	@Override
	public Map<String, Object> putWalletBalanceOnHold(Long studentId, Money money) {

		return lockWalletBalance(studentId, money);
	}

	private @Valid Map<String, Object> lockWalletBalance(Long studentId, Money money){

		Wallet wallet = null;
		FinancialTransaction financialTransaction = null;
		Map<String, Object> walletMap = new HashMap<String, Object>();

		Student student = studentService.getStudentById(studentId).get();

		financialTransaction = FinancialTransactionManager.startNewFinancialTransaction();
		financialTransaction.getPaymentTransaction().setMoney(money);
		financialTransaction.getPaymentTransaction().setSource(null);// only for transfers
		financialTransaction.getPaymentTransaction().setDestination(studentId);
		financialTransaction.getPaymentTransaction().setPaymentSystem("INTERNAL");// Not implemented yet, set
																					// internal for all
		financialTransaction.getPaymentTransaction().setTransactionStatus(1);
		financialTransaction.getPaymentTransaction().setTransactionType(2);

		if (!student.getWallet().getCurrency()
				.equals(financialTransaction.getPaymentTransaction().getMoney().getCurrency())) {
			financialTransaction.getPaymentTransaction()
					.setMoney(WalletUtils.convertCurrency(WalletConstants.WALLET_AVAILABLE_CURRENCY.INDIA.getCURRENCY(),
							financialTransaction.getPaymentTransaction().getMoney()));
		}
		student.getWallet().setOnHold(financialTransaction.getPaymentTransaction().getMoney().getValue());
		student.getWallet().setAvailableBalance(student.getWallet().getAvailableBalance()
				.subtract(financialTransaction.getPaymentTransaction().getMoney().getValue()));

		wallet = saveWallet(student.getWallet());

		walletMap.put("Wallet", wallet);
		walletMap.put("transactionId", FinancialTransactionManager.commitOnHoldTransaction(financialTransaction));
		walletMap.put("onHoldSuccess", true);

		return walletMap;
	}

	public Map<String, Object> depositOnHoldBalance(Long transactionId, Money money) {


		Map<String, Object> walletMap = new HashMap<String, Object>();
		boolean releaseDiff=WalletConstants.RELEASE_AUTHORIZED_WALLET_DIFFERENCE;
		try {

			return depositBalanceAmountOnHold(transactionId, money, releaseDiff);

		} catch (ApplicationException exp) {
			walletMap.put("depositMoneySuccess", false);
			walletMap.put("Error", exp.getMessage());
		}

		return walletMap;
	}

	private Map<String, Object> depositBalanceAmountOnHold(Long transactionId, Money money, boolean releaseDiff) throws ApplicationException {

		Map<String, Object> walletMap = new HashMap<String, Object>();
		Wallet wallet = null;
		FinancialTransaction onHoldFinancialTransaction = null;

		FinancialTransaction depositFinancialTransaction = null;

		onHoldFinancialTransaction = FinancialTransactionManager.startNewFinancialTransaction();
		onHoldFinancialTransaction.setPaymentTransactionForTransactionId(transactionId);
		if(onHoldFinancialTransaction.getPaymentTransaction().getTransactionType()!=2 || onHoldFinancialTransaction.getPaymentTransaction().getTransactionStatus() != 4) {
			throw new ApplicationException("Wrong authorization transaction id passed. Please check the transaction id.");
		}

		Student student = studentService
				.getStudentById(onHoldFinancialTransaction.getPaymentTransaction().getDestination()).get();

		depositFinancialTransaction = FinancialTransactionManager.startNewFinancialTransaction();
		depositFinancialTransaction.getPaymentTransaction();

		depositFinancialTransaction.getPaymentTransaction().setMoney(money);
		depositFinancialTransaction.getPaymentTransaction().setSource(null);// null for now
		depositFinancialTransaction.getPaymentTransaction().setDestination(student.getId());
		depositFinancialTransaction.getPaymentTransaction().setPaymentSystem("INTERNAL");// Not implemented yet, set
		// internal for all
		depositFinancialTransaction.getPaymentTransaction().setTransactionStatus(1);
		depositFinancialTransaction.getPaymentTransaction().setTransactionType(3);

		if (!student.getWallet().getCurrency()
				.equals(depositFinancialTransaction.getPaymentTransaction().getMoney().getCurrency())) {
			depositFinancialTransaction.getPaymentTransaction()
					.setMoney(WalletUtils.convertCurrency(WalletConstants.WALLET_AVAILABLE_CURRENCY.INDIA.getCURRENCY(),
							depositFinancialTransaction.getPaymentTransaction().getMoney()));
		}
		
		if(depositFinancialTransaction.getPaymentTransaction().getMoney().getValue().compareTo(student.getWallet().getOnHold())== 1) {
			FinancialTransactionManager.commitFailedTransaction(depositFinancialTransaction);
			throw new ApplicationException("Authorized amount is less than requested amount, so not able to deposit.");
		}
		
		BigDecimal moneyDiff=onHoldFinancialTransaction.getPaymentTransaction().getMoney().getValue().subtract(depositFinancialTransaction.getPaymentTransaction().getMoney().getValue());
		
		if(releaseDiff) {
			
			student.getWallet().setOnHold(new BigDecimal(0));
			releaseAuthorizedFunds(depositFinancialTransaction.getPaymentTransaction());
			student.getWallet().setBalance(student.getWallet().getBalance().subtract(depositFinancialTransaction.getPaymentTransaction().getMoney().getValue()));
			student.getWallet().setAvailableBalance(student.getWallet().getAvailableBalance().add(moneyDiff));
			FinancialTransactionManager.commitSuccessfulTransaction(onHoldFinancialTransaction);
			
		}
		else {
			
			student.getWallet().setOnHold(moneyDiff);
			releaseAuthorizedFunds(depositFinancialTransaction.getPaymentTransaction());
			student.getWallet().setBalance(student.getWallet().getBalance().subtract(depositFinancialTransaction.getPaymentTransaction().getMoney().getValue()));
			
		}
		
		wallet = saveWallet(student.getWallet());
		
		walletMap.put("Wallet", wallet);
		walletMap.put("transactionId", FinancialTransactionManager.commitSuccessfulTransaction(depositFinancialTransaction));
		walletMap.put("depositMoneySuccess", true);

		return walletMap;

	}
	
	private boolean releaseAuthorizedFunds(PaymentTransaction transaction) {
		return true;
	}

}
