Narrative:
In order to identify the teaching sessions
As a lecturer
I want to import a MyCampus course

Scenario: return sessions
Given a valid MyCampus course co
When I try to retrieve the course information 
Then a function should accept this course and the function should return the sessions for this course

Scenario: invalid Mycampus course
Given a false MyCampus course whatever
When I try to retrieve the course information 
Then a function should accept this course and the function should return false