Narrative:
IN ORDER TO identify the teaching sessions
AS A lecturer
I WANT TO import a MyCampus course

Scenario: 
GIVEN a valid MyCampus course ID
WHEN I try to retrieve the course information 
THEN a function should accept this course ID
AND the function should return the sessions for this course

Scenario: 
GIVEN a false MyCampus course ID
WHEN I try to retrieve the course information 
THEN a function should accept this course ID
AND the function should return an error 

Scenario: 
GIVEN a valid MyCampus course ID
WHEN I try to retrieve the course information 
THEN a function should accept this course ID
AND the function should not return any errors
