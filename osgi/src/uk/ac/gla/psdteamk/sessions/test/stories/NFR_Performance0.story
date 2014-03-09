Narrative:
In order to fulfill a non functional requirement
As a system
I want to support at least 100 courses

Scenario: add 100 courses to the database
Given a course name testCourse
When I try to add it a 100 times to the database
Then the output is true 
