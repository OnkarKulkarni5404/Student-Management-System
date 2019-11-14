package com.StudentClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StudentClient.beans.Branch;
import com.StudentClient.beans.Gender;
import com.StudentClient.beans.Student;
import com.StudentClient.service.StudentService;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class HController {

	@Autowired
	StudentService service;
	
	@GetMapping("/hello")
	String testit(){
		return "hello Student-Update is working Fine";
	}
		
	@PutMapping("/update")
	public String updateStudent(@RequestBody Student s){
		return service.updateStudent(s);

	}
	
	@GetMapping("/branch/{branch}")
	public List<Student> showByBranch(@PathVariable Branch branch)
	{
		return service.showByBranch(branch);
	}
	
	@GetMapping("/gender/{gender}")
	public List<Student> showByGender(@PathVariable Gender gender){
		return service.showByGender(gender);
	}
	
	
}