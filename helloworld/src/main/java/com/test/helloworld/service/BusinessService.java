package com.test.helloworld.service;

import org.springframework.stereotype.Component;

import com.test.helloworld.model.HelloWorld;

@Component
public class BusinessService {

	public HelloWorld getHelloWorld() {
		HelloWorld HW = new HelloWorld();
		return HW;
	}
}
