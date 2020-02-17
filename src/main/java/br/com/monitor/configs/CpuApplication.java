package br.com.monitor.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import br.com.monitor.controllers.CpuController;

@SpringBootApplication
@Configuration
@ComponentScan(basePackageClasses = CpuController.class)
public class CpuApplication {

	/* INICIALIZANDO SPRING BOOT */
	public static void main(String[] args) {
		SpringApplication.run(CpuApplication.class, args);
	}
	
}
