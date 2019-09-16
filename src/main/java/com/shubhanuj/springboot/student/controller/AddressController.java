/**
 * 
 */
package com.shubhanuj.springboot.student.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhanuj.springboot.student.model.Address;
import com.shubhanuj.springboot.student.service.AddressService;

/**
 * @author Shubhanuj
 *
 */

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@POST
	@RequestMapping("/add")
	public Map<String, Object> addNewAddress(@Valid @RequestBody Address address) {
		
		Map<String, Object> addressMap=new HashMap<String, Object>();
		addressMap.put("Address", addressService.createNewAddress(address));
		return addressMap;
		
	}
	
	
	//getAddressByStudent
	//update Address
	//delete Address

}
