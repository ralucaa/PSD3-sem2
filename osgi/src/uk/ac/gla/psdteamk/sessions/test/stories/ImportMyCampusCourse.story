Narrative:
In order to identify the teaching sessions
As a lecturer
I want to import a MyCampus course

Scenario: return sessions
Given a valid MyCampus course 1
When I try to retrieve the course information 
Then a function should accept this course and the function should return true

Scenario: invalid Mycampus course
Given a false MyCampus course 9823425325
When I try to retrieve the course information 
Then a function should accept this course and the function should return false