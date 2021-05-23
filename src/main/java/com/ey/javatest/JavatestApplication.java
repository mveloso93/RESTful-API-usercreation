package com.ey.javatest;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavatestApplication.class, args);
	}
	
	@Bean
	ServletRegistrationBean<WebServlet> h2servletRegistration() {
		ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>(
				new WebServlet());
		registrationBean.addUrlMappings("/h2-console/*");
		return registrationBean;
	}

}
