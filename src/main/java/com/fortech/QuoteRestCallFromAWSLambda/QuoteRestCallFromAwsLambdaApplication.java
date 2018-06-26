 package com.fortech.QuoteRestCallFromAWSLambda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;


@SpringBootApplication
public class QuoteRestCallFromAwsLambdaApplication {

	@Autowired
	private QuoteSpeechlet quoteSpeechlet;
	
	@Bean
	public ServletRegistrationBean registerServlet() {
		SpeechletServlet speechletServlet = new SpeechletServlet();
		speechletServlet.setSpeechlet(this.quoteSpeechlet);
		
		return new ServletRegistrationBean(speechletServlet, "/alexa");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuoteRestCallFromAwsLambdaApplication.class, args);
	}
}
