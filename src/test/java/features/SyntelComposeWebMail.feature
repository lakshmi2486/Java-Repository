Feature: Compose mail by Handle Mutilple Window 

@SkipComposeWebMail @ComposeWebMail 
Scenario: Compose mail by Handle Mutilple Window 
	Given Login into webapp mail 
	Then Click on the New Mail link 
	And Enter the email address and save into the draft 
	And Close the compose mail child window 
	And Navigate to Draft menu and open the saved draft email