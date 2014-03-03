Narrative:
In order to fulfill a non functional requirement
As a system
I want to support at least 10 sessions per course

Scenario: add 10 sessions to a course 1337
Given a course id 1337
When the database request is made
Then the output is true