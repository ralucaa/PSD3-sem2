Narrative: 
IN ORDER TO identify the timetable slots,
AS A lecturer,
I WANT TO add a session to a course.

Scenario: valid course
Given a sessionID 1 for course 1
When a user tries to add it to the database
Then it should be added to the database and the function should return true

Scenario: invalid course
Given a sessionID 2 for course -1
When a user tries to add it to the database
Then it should not be added to the database and the function should return false

Scenario: valid course, negative capacity
Given a sessionID 3 for course 3 with a capacity -1
When a user tries to add it to the database
Then it should not be added to the database and the function should return false