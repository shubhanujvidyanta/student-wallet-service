/**
 * 
 */
package com.shubhanuj.springboot.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubhanuj.springboot.student.model.PaymentTransaction;

/**
 * @author Shubhanuj
 *
 */

@Repository
public interface TransactionRepository extends JpaRepository<PaymentTransaction, Long>{

}
