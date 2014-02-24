Narrative: 
IN ORDER TO identify the timetable slots,
AS A lecturer,
I WANT TO add a session to a course.

Scenario: 
GIVEN a session for a valid course
WHEN a user tries to add it to the database
THEN it should be added to the database 
AND the function should return true.

Scenario: 
GIVEN a session for a missing course
WHEN a user tries to add it to the database
THEN THEN the session should not be added to the database 
AND the function should return false.

Scenario: 
GIVEN a session for course and a negative capacity
WHEN a user tries to add it to the database
THEN the session should not be added to the database 
AND the function should return false.