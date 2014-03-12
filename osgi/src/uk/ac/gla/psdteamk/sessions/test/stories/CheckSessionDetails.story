Narrative:
In order to know when sessions happen
As a lecturer
I want to see the details (time,location, students, tutor) or every timetable slot in a session

Scenario: get the session details
Given some nice session 2
When I want to check the session details
Then the function should fetch the session information from the database and the function should return time, location, students, tutors for that session

Scenario: invalid session 687xyz
Given an invalid session id with characters 687xyz
When I want to check the session details
Then the function should get no records from the database and the function should return an error

Scenario: get timetable slots
Given some nice session 1
When I want to check the timetable slots for this session
Then the program return all the timetable slots for this session
