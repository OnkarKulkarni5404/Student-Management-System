package com.Token.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class Hello {

	@GetMapping("/")
	public String hello()
	{
		return "Hello";
	}
	
}
