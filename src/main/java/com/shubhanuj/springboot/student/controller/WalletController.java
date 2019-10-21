/**
 * 
 */
package com.shubhanuj.springboot.student.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhanuj.springboot.student.model.Money;
import com.shubhanuj.springboot.student.service.WalletService;

/**
 * @author Shubhanuj
 *
 */

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	WalletService walletService;
	
	
	@POST
	@RequestMapping("/addMoney/{Id}")
	public Map<String, Object> addMoneyToWallet(@PathVariable("Id") Long studentId,
											@RequestBody @Valid Money money) {
		
		return walletService.addMoneyToStudentWalletFromExternalPayment(studentId, money);
	}
	
	@GET
	@RequestMapping("/getBalance/{Id}")
	public Map<String, Object> getWalletBalance(@PathVariable("Id") Long studentId) {
		
		return walletService.getWalletMapForStudent(studentId);
		
	}
	
	@POST
	@RequestMapping("/onHold/{Id}")
	public Map<String,Object> putWalletBalanceOnHold(@PathVariable("Id") Long studentId, @RequestBody @Valid Money money){
		
		return walletService.putWalletBalanceOnHold(studentId, money);
	}
	
	@PUT
	@RequestMapping("/depositHold/{Id}")
	public Map<String,Object> depositOnHoldBalance(@PathVariable("Id") Long transactionId, @RequestBody @Valid Money money){
		
		return walletService.depositOnHoldBalance(transactionId, money);
	}
	

	
	//putOnHold
	//deposit
	//releaseHold
	//refund
	
	//introduce transaction
	//use Utils methods, otherwise code redundancy
}
