/**
 * 
 */
package com.shubhanuj.springboot.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubhanuj.springboot.student.model.Student;

/**
 * @author Shubhanuj
 * This class provides methods to save, update and delete Student object
 *
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByEmail(String email);
	Optional<Student> findById(Long Id);

}
