Narrative: 
In order to identify the timetable slots,
As a lecturer,
I want to add a session to a course.

Scenario: valid course
Given a new session for course 1
When a user tries to add it to the database
Then the result of the addition should be true

Scenario: invalid course
Given a new session for course -1
When a user tries to add it to the database
Then the result of the addition should be false