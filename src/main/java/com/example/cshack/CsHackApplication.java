package com.example.cshack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsHackApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CsHackApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Hello world");
	}
}
