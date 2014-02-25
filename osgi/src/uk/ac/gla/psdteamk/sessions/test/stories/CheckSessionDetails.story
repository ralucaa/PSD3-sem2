Narrative:
IN ORDER TO know when sessions happen
AS A lecturer
I WANT TO see the details (time,location, students, tutor) or every timetable slot in a session

Scenario: 
GIVEN a session ID
WHEN I want to check the session details
THEN the function should fetch the session information from the database 
AND the function should return time, location, students, tutors for that session

Scenario: 
GIVEN a fake session ID	
WHEN I check the session details 
THEN the function should get no records from the database
AND the function should return an error
