Narrative:
In order to fulfill a non functional requirement
As a system
I want to support at least 10 sessions per course

Scenario: add 10 sessions to a course
Given a course name testCourse
When the database request is made
Then the output is true