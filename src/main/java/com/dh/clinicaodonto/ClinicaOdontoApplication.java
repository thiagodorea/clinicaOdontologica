package com.dh.clinicaodonto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ClinicaOdontoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontoApplication.class, args);
	}

}
