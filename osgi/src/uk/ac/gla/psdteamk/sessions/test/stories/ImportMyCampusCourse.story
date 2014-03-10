Narrative:
In order to identify the teaching sessions
As a lecturer
I want to import a MyCampus course

Scenario: return sessions
Given a valid MyCampus course 1
When I try to retrieve the course information
Then the result of the import should be true

Scenario: invalid Mycampus course
Given a false MyCampus course 9823425325
When I try to retrieve the course information
Then the result of the import should be false