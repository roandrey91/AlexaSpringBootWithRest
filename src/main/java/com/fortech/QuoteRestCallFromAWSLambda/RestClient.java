package com.fortech.QuoteRestCallFromAWSLambda;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
	
	private Quote quote;
	
	public Quote getQuote(){
		RestTemplate restTemplate = new RestTemplate();
		this.quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		return quote;
	}
}
