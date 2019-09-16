/**
 * 
 */
package com.shubhanuj.springboot.student.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhanuj.springboot.student.model.Address;
import com.shubhanuj.springboot.student.repository.AddressRepository;

/**
 * @author Shubhanuj
 *
 */


@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	public @Valid Address createNewAddress(Address address) {
		return addressRepository.save(address);
	}

}
