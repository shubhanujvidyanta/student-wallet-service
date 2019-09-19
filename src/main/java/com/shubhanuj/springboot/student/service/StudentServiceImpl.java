/**
 * 
 */
package com.shubhanuj.springboot.student.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhanuj.springboot.student.exception.CreateResourceException;
import com.shubhanuj.springboot.student.exception.EncryptionException;
import com.shubhanuj.springboot.student.model.LoginPOJO;
import com.shubhanuj.springboot.student.model.Student;
import com.shubhanuj.springboot.student.model.Wallet;
import com.shubhanuj.springboot.student.repository.StudentRepository;
import com.shubhanuj.springboot.student.utils.StudentUtils;

/**
 * @author Shubhanuj
 *
 */

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	WalletService walletService;

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.StudentService#registerStudent(com.shubhanuj.springboot.student.model.Student)
	 */
	@Override
	public Map<String,Object> registerStudent(Student student) {
		Map<String,Object> studentMap=new HashMap<String,Object>();
		studentMap.put("Student", studentRepository.save(student));
		studentMap.put("registerStudentSuccess", true);
		return studentMap;
	}

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.StudentService#loginStudent(com.shubhanuj.springboot.student.model.LoginPOJO)
	 */
	@Override
	public Map<String, Object> loginStudent(LoginPOJO login) {

		Map<String, Object> loginResult = new HashMap<String, Object>();

		try {
			String email = login.getEmail();
			String encryptedPassword = login.getEncryptedPassword();

			Student student = studentRepository.findByEmail(email);

			if (student == null) {
				loginResult.put("loginSuccess", false);
				loginResult.put("Error", "No account available against the email id.");

				return loginResult;
			}
			if (student.isPasswordEncrypted()) {
				if (student.getEncryptedPassword().equals(encryptedPassword)) {
					loginResult.put("loginSuccess", true);
					loginResult.put("student", student);
				}
			} else {
				loginResult.put("loginSuccess", false);
			}

		} catch (EncryptionException exp) {
			exp.printStackTrace();
		}
		return loginResult;

	}

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.StudentService#getStudentById(java.lang.Long)
	 */
	@Override
	public Map<String,Object> getStudentById(Long studentId) {
		
		Map<String,Object> studentMap=new HashMap<String,Object>();
		if (studentRepository.findById(studentId).isPresent()) {
			studentMap.put("Student", studentRepository.findById(studentId).get());
		} else {
			studentMap.put("Student", null);
		}

		return studentMap;
	}

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.StudentService#getStudentByEmail(java.lang.String)
	 */
	@Override
	public Map<String,Object> getStudentByEmail(String email) {
		Map<String,Object> studentMap=new HashMap<String,Object>();
		
			studentMap.put("Student", studentRepository.findByEmail(email));
		return studentMap;

	}

	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.StudentService#createWalletForStudent(java.lang.Long)
	 */
	@Override
	public Map<String, Object> createWalletForStudent(Long studentId) {
		
		Wallet wallet = null;
		BigDecimal zero = null;
		Map<String, Object> walletMap=new HashMap<String, Object>();

		try {
			Student student = (Student) getStudentById(studentId).get("Student");
			
			if (student != null) {
				String currency = StudentUtils.getCurrencyForCountry(student.getCountry());
				if (currency == null) {
					throw new CreateResourceException(
							"Wallet creation failed since the user's country is not supported for wallet service.");
				}
				wallet = new Wallet();
				zero = new BigDecimal(0);
				wallet.setAvailableBalance(zero);
				wallet.setCurrency(currency);
				wallet.setBalance(zero);
				wallet.setOnHold(zero);
				wallet.setStatus(1);
				wallet.setStudentId(studentId);
				walletService.saveWallet(wallet);
				student.setWallet(wallet);
				studentRepository.save(student);
				
				walletMap.put("Wallet", wallet);
				walletMap.put("createWalletSuccess", true);
			}
		} catch(CreateResourceException exp) {
			walletMap.put("Error", exp.getMessage());
			walletMap.put("createWalletSuccess", false);
		} catch (Exception exp) {
			walletMap.put("Error", exp.getMessage());
			walletMap.put("createWalletSuccess", false);
		}

		return walletMap;
	}

}
