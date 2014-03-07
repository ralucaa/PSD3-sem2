Narrative:
In order to authenticate in the system
As a user
I want to be authenticated using the MyCampus single sign-on service

Scenario: user credentials are valid
Given a username 1111111A and a password 1111111A 
When the user tries to authenticate
Then the user should be authenticated and a positive integer should be returned

Scenario: user credentials are valid
Given a username 2222222A and a password 2222222A 
When the user tries to authenticate
Then the user should be authenticated and a positive integer should be returned

Scenario: user credentials are valid
Given a username 3333333E and a password 3333333E 
When the user tries to authenticate
Then the user should be authenticated and a positive integer should be returned

Scenario: user credentials are invalid
Given a username badusername and a password wrongpassword 
When the user tries to authenticate
Then the user should not be authenticated and -1 should be returned