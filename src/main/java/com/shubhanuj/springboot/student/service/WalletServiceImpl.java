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
	public Map<String,Object> addMoneyToStudentWallet(Long studentId, Money money) {
		
		Map<String,Object> walletMap=new HashMap<String, Object>();
		try {
			Student student=(Student) studentService.getStudentById(studentId).get("Student");
			walletMap.put("Wallet", addMoneyToWallet(student.getWallet(), money));
		walletMap.put("addMoneySuccess", true);
		}
		catch(ResourceNotAvailableException exp) {
			walletMap.put("addMoneySuccess", false);
			walletMap.put("Error", exp.getMessage());
		}
		
		return walletMap;
	}

	private @Valid Wallet addMoneyToWallet(Wallet wallet, Money money) throws ResourceNotAvailableException {
		
		if( wallet == null || money == null) {
			if(wallet == null)
				throw new ResourceNotAvailableException("Wallet is not available.");
			throw new ResourceNotAvailableException("Money is not available. Please check your input again.");
		}

		if (!wallet.getCurrency().equals(money.getCurrency())) {
			money = WalletUtils.convertCurrency(WalletConstants.WALLET_AVAILABLE_CURRENCY.INDIA.getCURRENCY(),
					money);
		}
		wallet.setBalance(wallet.getBalance().add(money.getValue()));
		wallet.setAvailableBalance(wallet.getAvailableBalance().add(money.getValue()));
		return saveWallet(wallet);
	}

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.WalletService#getWalletForStudent(java.lang.Long)
	 */
	@Override
	public Map<String,Object> getWalletForStudent(Long studentId) {
		
		Map<String,Object> walletMap=new HashMap<String, Object>();
		Student student=(Student) studentService.getStudentById(studentId).get("Student");
		
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
