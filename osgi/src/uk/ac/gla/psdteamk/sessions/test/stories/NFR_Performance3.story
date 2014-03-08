Narrative:
In order to meet the technical requirements
As a system
I want to support at least 20 different timetable slots per session

Scenario: session slot num greater than 20
Given an admin tries to add more than 20 timetable slots to the session 03
When a database operation is performed
Then it should do more than 20 insertions correctly
