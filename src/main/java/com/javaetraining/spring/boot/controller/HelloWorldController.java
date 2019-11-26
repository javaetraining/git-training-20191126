package com.javaetraining.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javaetraining.spring.boot.model.HelloWorldBean;

// changes by user 1
@RestController
public class HelloWorldController {

	@GetMapping
	public String defaultHello() {
		return "Default Hello from Spring Boot";
	}

	@GetMapping(path = "/say-hello")
	public String sayHello() {
		return "Hello from Spring Boot";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello from Spring Boot");
	}

	
	@GetMapping(path = "/say-hello/{name}")
	public String sayHello(@PathVariable("name") String name) {
		return String.format("Hello %s, Welcome to Spring Boot Online Training", name);
	}
	// Changes by user 2
}
