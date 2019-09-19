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
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	/* (non-Javadoc)
	 * @see com.shubhanuj.springboot.student.service.AddressService#createNewAddress(com.shubhanuj.springboot.student.model.Address)
	 */
	@Override
	public @Valid Address createNewAddress(Address address) {
		return addressRepository.save(address);
	}

}
