Narrative: 
In order to identify the timetable slots,
As a lecturer,
I want to add a session to a course.

Scenario: valid course
Given a sessionID 1 for course 1
When a user tries to add it to the database
Then it should be added to the database and the function should return true

Scenario: invalid course
Given a sessionID 2 for course -1
When a user tries to add it to the database
Then it should not be added to the database and the function should return false