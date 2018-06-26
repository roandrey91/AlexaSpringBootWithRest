package com.fortech.QuoteRestCallFromAWSLambda.mathService;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

import static com.fortech.QuoteRestCallFromAWSLambda.utils.MathResponseHalper.response;

public class MathIntents {

	
	public static SpeechletResponse multiply(IntentRequest request, Session session) {
		Intent intent = request.getIntent();
		int result = Integer.valueOf(intent.getSlot("x").getValue()) * Integer.valueOf(intent.getSlot("y").getValue());
		
		return response("Multiplication", "The multiplication result is " + result);
	}
	
	
	
}
