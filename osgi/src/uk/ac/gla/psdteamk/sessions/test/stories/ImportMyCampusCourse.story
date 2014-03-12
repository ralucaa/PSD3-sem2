Narrative:
In order to identify the teaching sessions
As a lecturer
I want to import a MyCampus course

Scenario: add a valid course
Given a MyCampus course with title Algorithmics
When I try to import the MyCampus course
Then the result of the MyCampus import should be true

Scenario: add an invalid course
Given a MyCampus course with title Not A Course
When I try to import the MyCampus course
Then the result of the MyCampus import should be false

Scenario: add an existing course
Given a MyCampus course with title Operating Systems
When I try to import the MyCampus course
Then the result of the MyCampus import should be false
