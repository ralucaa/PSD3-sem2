Narrative:
In order to authenticate in the system
As a user
I want to be authenticated using the MyCampus single sign-on service

Scenario: user credentials are valid
Given a username vlad and a password vlad 
When the user tries to authenticate
Then he should be authenticated and an Account object should be returned

Scenario: user credentials are invalid
Given a username badusername and a password wrongpassword 
When the user tries to authenticate
Then he should not be authenticated and null should be returned