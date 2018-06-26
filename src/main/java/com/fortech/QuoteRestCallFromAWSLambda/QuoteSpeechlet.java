package com.fortech.QuoteRestCallFromAWSLambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import com.fortech.QuoteRestCallFromAWSLambda.mathService.MathIntents;



@Service
public class QuoteSpeechlet implements Speechlet{

	Logger logger = LoggerFactory.getLogger(QuoteSpeechlet.class);
	
	private RestClient restClient;
	
	private final String WELCOME_MESAGE = "Welcome to Read Quote, you can ask me to play, read, "
										+ "get a random Quote from an external API wich return Spring qoute"; 
	private final String REPROPMT_USER = "What do you want to do ?";
	
	@Value(value = "${application.id}")
	private String applicationId;
	
	public QuoteSpeechlet(RestClient restClient){
		this.restClient = restClient;
	}
	
	@Override
	public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
		if (session.getApplication().getApplicationId().equalsIgnoreCase(applicationId)) {
		       Intent intent =intentRequest.getIntent();
		       logger.debug("onIntent  requestId={}, sessionId={} " + intentRequest.getRequestId() + session.getSessionId());
		       	if (intent == null) {
		            throw new SpeechletException("Unrecognized intent!");
				}
		       	if (intent.getName().equals("MathIntent")){
		       		logger.debug("MathIntent  requestId={}, sessionId={} " + intentRequest.getRequestId() + session.getSessionId());
		       		return  MathIntents.multiply(intentRequest, session);
		       	}
		       	return processCommand(intent);
				}else {
		            throw new SpeechletException("You are not authorized to access this skill. Cease & desist, and have a nice day.");
				}
	}

	private SpeechletResponse processCommand(Intent intent) {
		Quote quote = null;
		String cardTitle = "Random quote";
		
		quote = this.restClient.getQuote();
		
	
		SimpleCard card = new SimpleCard();
		card.setTitle(cardTitle);
		card.setContent(quote.getValue().getQuote().toString());
		
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(quote.getValue().getQuote().toString());
		
		return SpeechletResponse.newTellResponse(speech, card);
	}
	
	@Override
	public SpeechletResponse onLaunch(LaunchRequest arg0, Session arg1) throws SpeechletException {
		logger.debug("onSessionStarted requestId={}, sessionId={}", arg0.getRequestId(), arg1.getSessionId());
		return getWelcomeResponse();
	}
	
	public SpeechletResponse getWelcomeResponse() {
	  return  AskResponseHelper.newAskResponse(WELCOME_MESAGE, false, REPROPMT_USER, false);
	}
	
	@Override
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1) throws SpeechletException {
		// TODO Auto-generated method stub
		
	}

	
	
}
