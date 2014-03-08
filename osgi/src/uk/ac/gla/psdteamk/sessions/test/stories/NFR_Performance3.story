Narrative:
In order to meet the technical requirements
As a system
I want to support at least 20 different timetable slots per session

Scenario: session slot num greater than 20
Given to an admin tries to get a session 03
When I try to retrieve the session timetable slot number
Then a session slot number should be greater or equal to 20 
