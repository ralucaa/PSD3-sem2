Narrative:
IN ORDER TO meet the technical requirements
AS A system
I WANT TO support at least 20 different timetable slots per session

Scenario: 
Given a session $s
When I try to retrieve the session timetable slot number
Then a session slot number should be greater or equal to 20 
