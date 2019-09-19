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
import com.shubhanuj.springboot.student.transaction.TransactionManager;
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
	
	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.WalletService#addMoneyToStudentWallet(java.lang.Long, com.shubhanuj.springboot.student.model.Money)
	 */
	@Override
	public Map<String,Object> addMoneyToStudentWalletFromExternalPayment(Long studentId, Money money) {
		
		Map<String,Object> walletMap=new HashMap<String, Object>();
		try {
			FinancialTransaction finTrans=TransactionManager.startFinancialTransaction();
			finTrans.getPaymentTransaction().setMoney(money);
			finTrans.getPaymentTransaction().setSource(null);
			finTrans.getPaymentTransaction().setDestination(studentId);
			finTrans.getPaymentTransaction().setPaymentSystem("INTERNAL");//Not implemented yet, set internal for all
			finTrans.getPaymentTransaction().setTransactionStatus(1);
			finTrans.getPaymentTransaction().setTransactionType(1);
			walletMap.put("Wallet", addMoneyToWallet(finTrans));
		walletMap.put("addMoneySuccess", true);
		}
		catch(ResourceNotAvailableException exp) {
			walletMap.put("addMoneySuccess", false);
			walletMap.put("Error", exp.getMessage());
		}
		
		return walletMap;
	}

	private @Valid Wallet addMoneyToWallet(FinancialTransaction financialTransaction) throws ResourceNotAvailableException {
		
		if( financialTransaction == null) {
			throw new ResourceNotAvailableException("FinancialTransaction is not available.");
		}
		
		Student destStudent=studentService.getStudentById(financialTransaction.getPaymentTransaction().getDestination()).get();

		if (!destStudent.getWallet().getCurrency().equals(financialTransaction.getPaymentTransaction().getMoney().getCurrency())) {
			financialTransaction.getPaymentTransaction().setMoney(WalletUtils.convertCurrency(WalletConstants.WALLET_AVAILABLE_CURRENCY.INDIA.getCURRENCY(),
					financialTransaction.getPaymentTransaction().getMoney())) ;
		}
		destStudent.getWallet().setBalance(destStudent.getWallet().getBalance().add(financialTransaction.getPaymentTransaction().getMoney().getValue()));
		destStudent.getWallet().setAvailableBalance(destStudent.getWallet().getAvailableBalance().add(financialTransaction.getPaymentTransaction().getMoney().getValue()));
		return saveWallet(destStudent.getWallet());
	}

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.WalletService#getWalletForStudent(java.lang.Long)
	 */
	@Override
	public Map<String,Object> getWalletForStudent(Long studentId) {
		
		Map<String,Object> walletMap=new HashMap<String, Object>();
		Student student=(Student) studentService.getStudentById(studentId).get();
		
		walletMap.put("Wallet", student.getWallet());
		
		return walletMap;

	}
	
	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.WalletService#saveWallet(com.shubhanuj.springboot.student.model.Wallet)
	 */
	@Override
	public @Valid Wallet saveWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}

	
}
