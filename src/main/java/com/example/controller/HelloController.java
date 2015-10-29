package com.example.controller;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Greeting;
import com.example.exception.ResourceRefusedException;
import com.example.repository.TestRepository;

@RestController
public class HelloController {
	private static final String template = "Hello %s!";
	private static final String template2 = "I personally welcome you %s!";
	private final AtomicLong counter = new AtomicLong();
	
	TestRepository repository;
	
	@Autowired
	public HelloController(TestRepository repository){
		this.repository = repository;
	}
	public HelloController(){
	}
	
	@RequestMapping(value="/greeting",
	method=RequestMethod.GET,
	produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Greeting> greeting(@RequestParam(value="name",defaultValue="world") String name){
		Greeting greet = new Greeting(counter.incrementAndGet(),
									  String.format(template, name+repository.getAgentId()));
		ResponseEntity<Greeting> entity = new ResponseEntity<>(greet,HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value="/greeting/{name}",
	method=RequestMethod.GET,
	produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Greeting> personalGreeting(@PathVariable String name){
		if ("Mark".equalsIgnoreCase(name)) throw new ResourceRefusedException("Mark is not allowed");
		Greeting greet = new Greeting(counter.incrementAndGet(),
									  String.format(template2, name));
		ResponseEntity<Greeting> entity = new ResponseEntity<>(greet,HttpStatus.OK);
		return entity;
	}
	
		
}
