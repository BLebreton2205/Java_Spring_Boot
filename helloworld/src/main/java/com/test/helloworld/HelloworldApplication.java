package com.test.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.helloworld.model.HelloWorld;
import com.test.helloworld.service.BusinessService;

@SpringBootApplication
public class HelloworldApplication implements CommandLineRunner {
	
	@Autowired
	private BusinessService bs; 
	
	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		HelloWorld hw = bs.getHelloWorld();
		System.out.println(hw);
	}

}
