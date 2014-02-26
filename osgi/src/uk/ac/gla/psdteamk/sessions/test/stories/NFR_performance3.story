Narrative:
In order to meet the technical requirements
As A system
I want to support at least 20 different timetable slots per session

Scenario: session slot num greater than 20
Given a session $s
When I try to retrieve the session timetable slot number
Then a session slot number should be greater or equal to 20 
