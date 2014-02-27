Narrative:
In order to know when sessions happen
As A lecturer
I want to see the details (time,location, students, tutor) or every timetable slot in a session

Scenario: get the session details
Given a session 2
When I want to check the session details
Then the function should fetch the session information from the database and the function should return time, location, students, tutors for that session

Scenario: invalid session 29
Given a fake session ID	
When I check the session details 
Then the function should get no records from the database and the function should return an error
