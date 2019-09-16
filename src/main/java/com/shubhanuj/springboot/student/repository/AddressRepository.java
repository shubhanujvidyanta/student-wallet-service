/**
 * 
 */
package com.shubhanuj.springboot.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubhanuj.springboot.student.model.Address;

/**
 * @author Shubhanuj
 *
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}
