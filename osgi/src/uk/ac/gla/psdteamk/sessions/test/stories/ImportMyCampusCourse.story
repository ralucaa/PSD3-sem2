Narrative:
In order to identify the teaching sessions
As a lecturer
I want to import a MyCampus course

Scenario: return sessions
Given a valid MyCampus course with id 1
When I try to add it into the database
Then the result of the import should be true

Scenario: add an existing course 1
Given an already existing MyCampus course with id 1
When I try to add it into the database
Then the result of the import should be false
