package com.fortech.QuoteRestCallFromAWSLambda;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

	private RestClient restClient;
	
	public QuoteController(RestClient restClient) {
		this.restClient = restClient;
	}
	
	@GetMapping("/getQuote")
	public Quote getQuote() {
		return this.restClient.getQuote();
	}
}
