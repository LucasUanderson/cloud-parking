package com.tech.parking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@GetMapping
	public String hello() {
		return "Ola, mundo";
	}

}
