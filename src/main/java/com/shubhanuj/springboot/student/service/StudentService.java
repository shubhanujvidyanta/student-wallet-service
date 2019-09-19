package com.shubhanuj.springboot.student.service;

import java.util.Map;

import com.shubhanuj.springboot.student.model.LoginPOJO;
import com.shubhanuj.springboot.student.model.Student;

public interface StudentService {

	Map<String, Object> registerStudent(Student student);

	Map<String, Object> loginStudent(LoginPOJO login);

	Map<String, Object> getStudentById(Long studentId);

	Map<String, Object> getStudentByEmail(String email);

	Map<String, Object> createWalletForStudent(Long studentId);

}