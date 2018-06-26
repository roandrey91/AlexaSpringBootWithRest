 # README #

##Quote Rest Call With Alexa##

###This is a simple alexa skill which provide a random quote from an outside api and a short example how to use Alexa to make simple math operation###

Spring Boot-based application.

###Setup###
To run this example skill you need to do two things. The first is to deploy the example code on Heroku( I used Heroku because they provede https 
protocol and it is free for test purpose), and second is to configure the Alexa skill to point to our back-end.	
 
###Alexa Skill Setup###
Go to the Alexa Console and click Add a New Skill.
Set "RandomQuote" as the skill name and "random quote" as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, tell random quote multiply 5 and 10"
Copy the link from Heroku(you can use whatever cloud you want or you could try to deploy to AWS Lambda). Chose HTTPS and paste de link in Default Region, and adding /alexa to your link.
Below to default region you'll need to chose "My development endpoint is a sub-domain of a domain that has..."  Save Endpoint.
Copy the Intent Schema from the included IntentSchema.json.
Copy the Sample Utterances from the included SampleUtterances.txt. Click Next.
Go back to the skill Information tab and copy the appId. Paste the appId into the application.properties file, then update heroku with this change.
You are now able to start testing your sample skill! You should be able to go to the Echo webpage and see your skill enabled.
In order to test it, try to say some of the Sample Utterances from the Examples section below.
Your skill is now saved and once you are finished testing you can continue to publish your skill.
  

### Developer ###

* Muresan Andrei Gabriel
* m.andreigabi91@gmai.com
