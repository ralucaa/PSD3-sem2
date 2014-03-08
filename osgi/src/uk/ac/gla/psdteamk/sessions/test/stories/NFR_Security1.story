Narrative:
In order to have access to features specific for my type
As a user 
I want to the system to recognize my type and allow me correct permissions

Scenario: admin
Given a username 1111111A and a password 1111111A
When the user authenticates
Then he should be authenticated as a admin

Scenario: lecturer
Given a username 2222222A and a password 2222222A
When the user authenticates
Then he should be authenticated as a lecturer

Scenario: student
Given a username 3333333A and a password 3333333A
When the user authenticates
Then he should be authenticated as a student