/**
 * 
 */
package com.shubhanuj.springboot.student.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhanuj.springboot.student.model.LoginPOJO;
import com.shubhanuj.springboot.student.model.Student;
import com.shubhanuj.springboot.student.service.StudentService;

/**
 * @author Shubhanuj
 *
 */

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@POST
	@RequestMapping("/register/add")
	public Map<String, Object> addNewStudent(@Valid @RequestBody Student student) {

		return studentService.registerStudent(student);
	}

	@POST
	@RequestMapping("/login")
	public Map<String, Object> loginStudent(@RequestBody LoginPOJO login) {

		Map<String, Object> loginMap = studentService.loginStudent(login);
		if ((boolean) loginMap.get("loginSuccess"))
			loginMap.put("viewName", "MyAccountView");
		return loginMap;

	}

	@GET
	@RequestMapping("/getById/{Id}")
	public Map<String, Object> getStudentById(@PathVariable("Id") Long studentId) {

		return studentService.getStudentMapById(studentId);
	}

	@GET
	@RequestMapping("/getByEmail/{email}")
	public Map<String, Object> getStudentByEmail(@PathVariable("email") String email) {

		return studentService.getStudentMapByEmail(email);
	}

	@POST
	@RequestMapping("/createWallet/{Id}")
	public Map<String, Object> createWalletForStudent(@PathVariable("Id") Long studentId) {

		return studentService.createWalletForStudent(studentId);
	}

	// update student details

}
