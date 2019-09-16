/**
 * 
 */
package com.shubhanuj.springboot.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubhanuj.springboot.student.model.Wallet;

/**
 * @author Shubhanuj
 * This class provides methods to save, update and delete Student object
 *
 */

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
