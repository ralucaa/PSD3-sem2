Narrative:
In order to identify the teaching sessions
As A lecturer
I want to import a MyCampus course

Scenario: 
Given a valid MyCampus course co
When I try to retrieve the course information 
Then a function should accept this course and the function should return the sessions for this course

Scenario: 
Given a false MyCampus course co
When I try to retrieve the course information 
Then a function should accept this course and the function should return an error 

Scenario: 
Given a valid MyCampus course co
When I try to retrieve the course information 
Then a function should accept this course and the function should not return any errors
