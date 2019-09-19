package com.shubhanuj.springboot.student.service;

import java.util.Map;
import java.util.Optional;

import com.shubhanuj.springboot.student.model.LoginPOJO;
import com.shubhanuj.springboot.student.model.Student;

public interface StudentService {

	Map<String, Object> registerStudent(Student student);

	Map<String, Object> loginStudent(LoginPOJO login);

	Optional<Student> getStudentById(Long studentId);

	Map<String, Object> createWalletForStudent(Long studentId);

	Map<String, Object> getStudentMapById(Long studentId);

	Map<String, Object> getStudentMapByEmail(String email);

}