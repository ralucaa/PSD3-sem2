Narrative: 
IN ORDER TO identify the timetable slots,
AS A lecturer,
I WANT TO add a session to a course.

Scenario: 
Given a session 1 for a valid course
When a user tries to add it to the database
Then it should be added to the database 
and the function should return true

Scenario: 
Given a session 2 for a missing course
When a user tries to add it to the database
Then it should not be added to the database 
and the function should return false

Scenario: 
Given a session for a course and a negative capacity
When a user tries to add it to the database
Then it should not be added to the database 
and the function should return false